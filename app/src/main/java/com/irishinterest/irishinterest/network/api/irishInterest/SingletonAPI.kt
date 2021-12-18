package com.irishinterest.irishinterest.network.api.irishInterest

import com.irishinterest.irishinterest.network.api.irishInterest.IrishInterestApiInterface
import com.irishinterest.irishinterest.network.api.irishInterest.SingletonAPI
import com.irishinterest.irishinterest.network.helper.ApiConstants
import retrofit2.Retrofit
import kotlin.jvm.Synchronized
import retrofit2.converter.jackson.JacksonConverterFactory

class SingletonAPI {
    val apiInterface = retrofit!!.create(
        IrishInterestApiInterface::class.java
    )

    companion object {
        private val BASE_URL = ApiConstants.API_URL.key
        private var retrofit = retrofitClient
        private var irishInterestAPIBook: SingletonAPI? = null

        @JvmStatic
        @get:Synchronized
        val instance: SingletonAPI?
            get() {
                if (irishInterestAPIBook == null) {
                    irishInterestAPIBook = SingletonAPI()
                }
                return irishInterestAPIBook
            }
        private val retrofitClient: Retrofit?
            get() {
                if (retrofit == null) {
                    retrofit = Retrofit.Builder().baseUrl(BASE_URL)
                        .addConverterFactory(JacksonConverterFactory.create()).build()
                }
                return retrofit
            }
    }
}