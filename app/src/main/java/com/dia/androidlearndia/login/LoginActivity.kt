package com.dia.androidlearndia.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import com.dia.androidlearndia.R
import com.dia.androidlearndia.bottomnavigation.HomeActivity
import com.dia.androidlearndia.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private val viewModel: LoginViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {
            val phoneNumber = binding.etPhoneNumber.text.toString()
            val password = binding.etPassword.text.toString()

//            viewModel.loginValidation(phoneNumber, password)
            viewModel.login(phoneNumber, password)
        }

        setObserver()
    }

    private fun setObserver() {
        viewModel.loginValidation.observe(this@LoginActivity){
            if(it.isValid){
                binding.tvError.visibility =
                    View.VISIBLE
                binding.tvError.setTextColor(
                    getColor(R.color.Green))
                binding.tvError.text = it.message
            }else{
                binding.tvError.visibility =
                    View.VISIBLE
                binding.tvError.setTextColor(
                    getColor(R.color.Red))
                binding.tvError.text = it.message
            }
        }

        viewModel.loginData.observe(this@LoginActivity){
            Log.i("logger", "Login data $it")
            //save login data to local storage

            val home = Intent(this@LoginActivity, HomeActivity::class.java)
            startActivity(home)
            finish()
        }
    }
}