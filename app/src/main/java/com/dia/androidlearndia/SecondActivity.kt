package com.dia.androidlearndia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val userName = intent.getStringExtra("username")
        Log.i("Second Activity", "Username $userName")

        Toast.makeText(this@SecondActivity, "Username $userName", Toast.LENGTH_LONG).show()
    }
}