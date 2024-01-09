package com.dia.androidlearndia.retrofit

data class TransactionResponse(
    val success: Boolean,
    val message: String,
    val errorCode: String,
    val data: List<TransactionData>

)
