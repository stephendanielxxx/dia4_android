package com.dia.androidlearndia.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.dia.androidlearndia.sharedpref.SharedPrefHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class FavoriteViewModel @Inject constructor(
    private val sharedPrefHelper: SharedPrefHelper
): ViewModel() {

    //live data used internally in view model
    private val pokemonNameLiveData =
        MutableLiveData<String>()
    //accessed by view
    val pokemonName: LiveData<String> =
        pokemonNameLiveData

    fun printPokemonName(pokemonName: String){
//        Log.i("logger", "Pokemon name = $pokemonName")
        // emit value using live data
        pokemonNameLiveData.value = "Pokemon name = $pokemonName"
        sharedPrefHelper
            .setStoredName("POKEMON_NAME",
                pokemonName)
    }

    fun getPokemonName(){
        val name = sharedPrefHelper
            .getStoredName("POKEMON_NAME")
        name?.let {
            Log.i("logger",
                "Saved pokemon name = $it")
        }
    }
}