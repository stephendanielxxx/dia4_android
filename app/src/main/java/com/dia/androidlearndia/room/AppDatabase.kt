package com.dia.androidlearndia.room

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [Pokemon::class], version = 1)
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}