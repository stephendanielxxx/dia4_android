package com.dia.androidlearndia.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.dia.androidlearndia.datastore.DataStoreHelper
import com.dia.androidlearndia.room.AppDatabase
import com.dia.androidlearndia.room.Pokemon
import com.dia.androidlearndia.rv.PokemonModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val appDatabase: AppDatabase,
    private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    private val pokemonModelLiveData = MutableLiveData<List<PokemonModel>>()
    val pokemonModel: LiveData<List<PokemonModel>> = pokemonModelLiveData

    private val stringLiveData = MutableLiveData<String>()
    val stringData: LiveData<String> = stringLiveData

    fun savePokemon(pokemonName: String, pokemonDesc: String, pokemonColor: String) {
        // run insert using coroutines
        viewModelScope.launch {
            val newPokemon = Pokemon(
                pokemonName = pokemonName, pokemonDesc = pokemonDesc,
                pokemonColor = pokemonColor
            )
            appDatabase.pokemonDao().savePokemon(newPokemon)
        }
    }

    fun setDataStoreValue(pokemonName: String){
        viewModelScope.launch {
            dataStoreHelper.saveStringData(pokemonName)
        }
    }

    fun getDataStoreValue(){
        viewModelScope.launch {
            dataStoreHelper.stringData.flowOn(Dispatchers.IO).collect{
                stringLiveData.value = it
            }
        }
    }

    fun getAllPokemon() {
        viewModelScope.launch {
            appDatabase.pokemonDao().getAllPokemon().flowOn(Dispatchers.IO)
                .collect {
                    //send data to home fragment
                    val pokemonModels = it.map { pokemon ->
                        convertPokemon(pokemon)
                    }

                    pokemonModelLiveData.value = pokemonModels
                }
        }
    }

    private fun convertPokemon(pokemon: Pokemon): PokemonModel {
        return PokemonModel(
            pokemon.id,
            pokemon.pokemonName,
            pokemon.pokemonDesc,
            "",
            pokemon.pokemonColor
        )
    }
}