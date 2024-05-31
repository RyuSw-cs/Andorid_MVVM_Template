package com.ryusw.data.local.datasource

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.first
internal class AuthDataStore(
    @ApplicationContext private val context: Context
) {
    private val Context.dataStore by preferencesDataStore("AUTH")
    private val accessTokenPreference = stringPreferencesKey("ACCESS_TOKEN")
    suspend fun setAccessToken(accessToken: String) {
        context.dataStore.edit { preference ->
            preference[accessTokenPreference] = accessToken
        }
    }

    suspend fun getAccessToken() : String {
        return context.dataStore.data.first().let {
            it[accessTokenPreference] ?: ""
        }
    }
}