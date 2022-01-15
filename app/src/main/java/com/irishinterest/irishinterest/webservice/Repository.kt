package com.irishinterest.irishinterest.webservice

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class Repository constructor(
    baseURL: String = "https://irishinterest.ie/API2/rest/"
) {
    val service: WebService = Retrofit.Builder()
        .baseUrl(baseURL)
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .addConverterFactory(GsonConverterFactory.create())
        .build().create(WebService::class.java)
}