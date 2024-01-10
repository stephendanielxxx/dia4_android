package com.dia.androidlearndia.sharedpref

import android.content.Context
import androidx.preference.PreferenceManager

class SharedPrefHelper(val context: Context) {
    //get string value by key
    fun getStoredName(key: String): String?{
        val prefs = PreferenceManager.
        getDefaultSharedPreferences(context)
        return prefs.getString(key, "")
    }

    fun getIntValue(key: String): Int?{
        val prefs = PreferenceManager.
        getDefaultSharedPreferences(context)
        return prefs.getInt(key, 0)
    }

    fun setStoredName(key: String, value: String){
        PreferenceManager.
        getDefaultSharedPreferences(context)
            .edit()
            .putString(key, value)
            .apply()
    }

    fun setIntValue(key: String, value: Int){
        PreferenceManager.
        getDefaultSharedPreferences(context)
            .edit()
            .putInt(key, value)
            .apply()
    }
}