package com.dia.androidlearndia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContentView(R.layout.activity_main)

        Log.i("On Create", "On Create")

        //create intent to open second activity
        val intent = Intent(this@MainActivity, ComponentActivity::class.java)
        intent.putExtra("username", "Andre")
        startActivity(intent)
        finish()
    }

    override fun onStart() {
        super.onStart()
        Log.i("On Start", "On Start")
    }

    override fun onResume() {
        super.onResume()
        Log.i("On Resume", "On Resume")
    }

    override fun onPause() {
        super.onPause()
        Log.i("On Pause", "On Pause")
    }

    override fun onStop() {
        super.onStop()
        Log.i("On Stop", "On Stop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("On Destroy", "On Destroy")
    }

}