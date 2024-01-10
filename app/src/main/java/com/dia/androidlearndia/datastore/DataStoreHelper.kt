package com.dia.androidlearndia.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStoreHelper(private val context: Context) {
    companion object{
        val Context.dataStore: DataStore<Preferences> by preferencesDataStore("dia_datastore")
        val STRING_VALUE = stringPreferencesKey("STRING_VALUE")
    }

    suspend fun saveStringData(value: String){
        context.dataStore.edit {
            it[STRING_VALUE] = value
        }
    }

    val stringData: Flow<String> = context.dataStore.data.map {
        it[STRING_VALUE] ?: ""
    }

}










