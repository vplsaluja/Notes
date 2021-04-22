package org.anvtech.keepnotes

import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitClient {
     fun initRetrofitClient():RetrofitApi{
        val retrofit= Retrofit.Builder().baseUrl("http://10.0.2.2:8080").
        addCallAdapterFactory(RxJava3CallAdapterFactory.create())
            .addConverterFactory(GsonConverterFactory.create()).build();
        return retrofit.create(RetrofitApi::class.java)
    }
}