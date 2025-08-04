package com.jaamcoding.translatorapp.languages

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class LanguagesStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("languages")
        val INDEX_LANG = intPreferencesKey("index_lang")
    }

    val getIndexLang: //obtener
            Flow<Int> = context.dataStore.data.map { preference -> preference[INDEX_LANG] ?: 0 }

    suspend fun saveIndexLang(index: Int) { //guardar
        context.dataStore.edit { preference -> preference[INDEX_LANG] = index }
    }
}