package com.dia.androidlearndia.fragment

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel: ViewModel() {

    private val pokemonStatusLiveData = MutableLiveData<String>()
    val pokemonStatus: LiveData<String> = pokemonStatusLiveData

    fun changePokemonStatus(status: String){
        Log.i("logger", "Viewmodel pokemon status $status")
        pokemonStatusLiveData.value = status
    }
}