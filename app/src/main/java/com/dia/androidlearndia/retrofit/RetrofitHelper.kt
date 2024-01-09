package com.dia.androidlearndia.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitHelper {
    private const val BASE_URl = "http://10.0.2.2:4050/api/"

    private fun getHttpLogInterceptor(): HttpLoggingInterceptor{
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return httpLoggingInterceptor
    }

    private fun getOkHttpClient(): OkHttpClient{
        return OkHttpClient().newBuilder()
            .addInterceptor(getHttpLogInterceptor())
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()
    }

    private fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URl)
            .client(getOkHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    // initialize api service for api request
    val apiService: ApiService = getRetrofit().create(ApiService::class.java)
}