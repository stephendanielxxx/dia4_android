package com.dia.androidlearndia.rv

data class PokemonModel(
    val pokemonId:Int,
    val pokemonName: String,
    val pokemonDesc: String,
    val pokemonImage: String,
    val pokemonColor: String = ""
)
