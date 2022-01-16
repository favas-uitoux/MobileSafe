package com.project.myapplication.apiservice

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory




object ApiClient {

    var BASE_URL = "http://milantex.in/"
    private val client = OkHttpClient.Builder().build()
    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(client)
        .build()

    fun<T> getClient(service: Class<T>): T{
        return retrofit.create(service)
    }


//        var retrofit: Retrofit? = null
//
//        fun getClient(): Retrofit? {
//            if (retrofit == null) {
//
//                val logging = HttpLoggingInterceptor()
//                logging.setLevel(HttpLoggingInterceptor.Level.BODY)
//
//                val client: OkHttpClient = OkHttpClient.Builder()
//                    .addInterceptor(logging)
//                    .build()
//
//                retrofit = Retrofit.Builder()
//                    .baseUrl(BASE_URL)
//                    .client(client)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build()
//            }
//            return retrofit
//        }
    }
