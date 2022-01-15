package com.irishinterest.irishinterest.webservice;

import java.util.List;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WebService {

    //https://fake-poi-api.mytaxi.com/?p1Lat={Latitude1}&p1Lon={Longitude1}&p2Lat={Latitude2}&p2Lon={Longitude2}
    //eg: of Hamburg (53.694865, 9.757589 & 53.394655, 10.099891).
    //https://fake-poi-api.mytaxi.com/?p1Lat=53.694865&p1Lon=9.757589&p2Lat=53.394655&p2Lon=10.099891

//    @GET("/")
//    Flowable<TaxiCollection> getTaxis(
//            @Query("p1Lat") Double latitude1,
//            @Query("p1Lon") Double longitude1,
//            @Query("p2Lat") Double latitude2,
//            @Query("p2Lon") Double longitude2
//    );


    @GET("?value=authors&type=getAll&apiKey=testApiKey&offset=0")
    Flowable<List<Author>> authors();


}
