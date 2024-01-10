package com.dia.androidlearndia.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Insert
    suspend fun savePokemon(pokemon: Pokemon)

    @Query("select * from pokemon")
    fun getAllPokemon(): Flow<List<Pokemon>>
}