package com.dia.androidlearndia.retrofit

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // list endpoint/api

    @GET("v1/auth/login")
    suspend fun login(@Query("phone_number") phoneNumber: String,
                      @Query("password") password: String): Response<LoginResponse>

    @GET("v1/user/history-transaction")
    suspend fun transactionHistory(): Response<TransactionResponse>
}