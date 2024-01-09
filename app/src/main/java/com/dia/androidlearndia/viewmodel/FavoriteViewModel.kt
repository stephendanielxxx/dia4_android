package com.dia.androidlearndia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class FavoriteViewModel: ViewModel() {

    //live data used internally in view model
    private val pokemonNameLiveData = MutableLiveData<String>()
    //accessed by view
    val pokemonName: LiveData<String> = pokemonNameLiveData
    fun printPokemonName(pokemonName: String){
//        Log.i("logger", "Pokemon name = $pokemonName")
        // emit value using live data
        pokemonNameLiveData.value = "Pokemon name = $pokemonName"
    }
}