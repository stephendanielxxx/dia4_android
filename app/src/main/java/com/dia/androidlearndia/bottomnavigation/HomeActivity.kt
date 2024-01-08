package com.dia.androidlearndia.bottomnavigation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.dia.androidlearndia.R
import com.dia.androidlearndia.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}