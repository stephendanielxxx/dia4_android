package com.dia.androidlearndia.retrofit

data class LoginResponse(
    val success: Boolean,
    val message: String,
    val errorCode: String,
    val data: LoginData?
)
