package com.dia.androidlearndia.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dia.androidlearndia.retrofit.LoginData
import com.dia.androidlearndia.retrofit.LoginResponse
import com.dia.androidlearndia.retrofit.RetrofitHelper
import com.google.gson.Gson
import kotlinx.coroutines.launch

class LoginViewModel: ViewModel() {

    private val loginValidationLiveData = MutableLiveData<LoginValidationData>()
    val loginValidation: LiveData<LoginValidationData> = loginValidationLiveData

    private val loginDataLiveData = MutableLiveData<LoginData>()
    val loginData: LiveData<LoginData> = loginDataLiveData

    private val apiService = RetrofitHelper.apiService

    fun login(phoneNumber: String, password: String){
        if(validatePhoneNumber(phoneNumber) && validatePassword(password)){
            //request api using coroutines
            viewModelScope.launch {
                val loginResponse = apiService.login(phoneNumber, password)
                if(loginResponse.isSuccessful){
                    val response = loginResponse.body()
                    response?.let {
                        if(it.success){
                            it.data?.let {loginData ->
                                loginDataLiveData.value = loginData
                            }
                        }
                    }
                }else{
                    var error: LoginValidationData
                    val errorJsonString = loginResponse.errorBody()?.string()
                    val errorResponse = Gson().fromJson(errorJsonString, LoginResponse::class.java)
                    if(errorResponse.errorCode == "001"){
                        error = LoginValidationData(false,
                            "Login gagal, user tidak terdafar atau password salah")
                    }else{
                        error = LoginValidationData(false,
                            "Login gagal, unknown error")
                    }
                    loginValidationLiveData.value = error
                }


            }
        }else{
            val error = LoginValidationData(false,
                "Login gagal, phone number/password tidak valid")
            loginValidationLiveData.value = error
        }
    }

    fun loginValidation(phoneNumber: String, password: String){
        if(validatePhoneNumber(phoneNumber) && validatePassword(password)){
            val loginValidationData = LoginValidationData(true,
                "Login sukses")
            loginValidationLiveData.value = loginValidationData
        }else{
            val error = LoginValidationData(false,
                "Login gagal, silahkan cek phone number/password")
            loginValidationLiveData.value = error
        }
    }

    private fun validatePhoneNumber(phoneNumber: String): Boolean{
        return phoneNumber.length in 9..12
    }

    private fun validatePassword(password: String): Boolean{
        return password.length in 6..12
    }

    private fun validateEmail(email: String): Boolean{
        return email.contains("@")
    }
}