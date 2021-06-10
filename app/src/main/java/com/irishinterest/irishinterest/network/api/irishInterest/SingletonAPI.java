package com.irishinterest.irishinterest.network.api.irishInterest;

import com.irishinterest.irishinterest.network.helper.ApiConstants;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class SingletonAPI {
    private static final String BASE_URL = ApiConstants.API_URL.getKey();
    private static Retrofit retrofit = getRetrofitClient();
    private IrishInterestApiInterface apiInterface = retrofit.create(IrishInterestApiInterface.class);
    private static SingletonAPI irishInterestAPIBook;

    public static synchronized SingletonAPI getInstance(){
        if(irishInterestAPIBook == null){
            irishInterestAPIBook = new SingletonAPI();
        }
        return  irishInterestAPIBook;
    }

    private static Retrofit getRetrofitClient(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(JacksonConverterFactory.create()).build();
        }
        return retrofit;
    }

    public IrishInterestApiInterface getApiInterface() {
        return apiInterface;
    }
}
