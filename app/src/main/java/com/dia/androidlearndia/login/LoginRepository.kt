package com.dia.androidlearndia.login

import com.dia.androidlearndia.retrofit.ApiService
import com.dia.androidlearndia.retrofit.LoginResponse
import retrofit2.Response
import javax.inject.Inject

class LoginRepository @Inject constructor(
    private val apiService: ApiService
) {
    suspend fun login(phoneNumber: String, password: String): Response<LoginResponse>{
        return apiService.login(phoneNumber, password)
    }
}