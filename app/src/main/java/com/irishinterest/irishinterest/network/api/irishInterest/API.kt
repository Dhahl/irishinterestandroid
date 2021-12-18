package com.irishinterest.irishinterest.network.api.irishInterest

import com.irishinterest.irishinterest.network.helper.ApiConstants
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object API {
    val instance: IrishInterestApiInterface = Retrofit.Builder()
        .baseUrl(ApiConstants.API_URL.key)
        .addConverterFactory(JacksonConverterFactory.create())
        .build()
        .create(IrishInterestApiInterface::class.java)
}