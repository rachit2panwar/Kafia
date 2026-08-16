package com.coffeeshop.core.domain.repository

import com.coffeeshop.core.domain.model.User
import com.coffeeshop.core.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface AuthRepository {
    suspend fun login(email: String, password: String): NetworkResult<User>
    suspend fun loginWithGoogle(idToken: String): NetworkResult<User>
    fun getSessionToken(): Flow<String?>
    suspend fun logout()
}
