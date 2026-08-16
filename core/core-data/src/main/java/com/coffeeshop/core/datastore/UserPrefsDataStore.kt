package com.coffeeshop.core.datastore

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

private val Context.dataStore by preferencesDataStore(name = "user_prefs")

@Singleton
class UserPrefsDataStore @Inject constructor(
    @ApplicationContext private val context: Context
) {
    companion object {
        private val JWT_TOKEN = stringPreferencesKey("jwt_token")
        private val USER_ID = stringPreferencesKey("user_id")
    }

    val jwtToken: Flow<String?> = context.dataStore.data.map { preferences ->
        preferences[JWT_TOKEN]
    }

    suspend fun saveJwtToken(token: String) {
        context.dataStore.edit { preferences ->
            preferences[JWT_TOKEN] = token
        }
    }

    suspend fun clear() {
        context.dataStore.edit { preferences ->
            preferences.clear()
        }
    }
}
