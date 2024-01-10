package com.dia.androidlearndia.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "pokemon")
data class Pokemon(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    @ColumnInfo(name = "name")
    val pokemonName: String,
    @ColumnInfo(name = "desc")
    val pokemonDesc: String,
    @ColumnInfo(name = "color")
    val pokemonColor: String
)
