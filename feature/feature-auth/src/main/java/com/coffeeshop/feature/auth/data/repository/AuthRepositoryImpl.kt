package com.coffeeshop.feature.auth.data.repository

import com.coffeeshop.core.datastore.UserPrefsDataStore
import com.coffeeshop.core.domain.model.User
import com.coffeeshop.core.network.AuthInterceptor
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.auth.data.mapper.toDomain
import com.coffeeshop.feature.auth.data.remote.AuthApi
import com.coffeeshop.feature.auth.data.remote.dto.LoginRequest
import com.coffeeshop.core.domain.repository.AuthRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(
    private val api: AuthApi,
    private val dataStore: UserPrefsDataStore,
    private val authInterceptor: AuthInterceptor
) : AuthRepository {

    override suspend fun login(email: String, password: String): NetworkResult<User> {
        return try {
            val response = api.login(LoginRequest(email, password))
            dataStore.saveJwtToken(response.token)
            authInterceptor.setToken(response.token)
            NetworkResult.Success(response.user.toDomain())
        } catch (e: Exception) {
            NetworkResult.Error(e.message ?: "Login failed", e)
        }
    }

    override suspend fun loginWithGoogle(idToken: String): NetworkResult<User> {
        // Implement OAuth logic
        return NetworkResult.Error("Not implemented")
    }

    override fun getSessionToken(): Flow<String?> = dataStore.jwtToken

    override suspend fun logout() {
        dataStore.clear()
        authInterceptor.setToken(null)
    }
}
