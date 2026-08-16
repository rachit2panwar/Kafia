package com.coffeeshop.feature.auth.data.remote

import com.coffeeshop.feature.auth.data.remote.dto.LoginRequest
import com.coffeeshop.feature.auth.data.remote.dto.AuthResponse
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApi {
    @POST("auth/login")
    suspend fun login(@Body request: LoginRequest): AuthResponse

    @POST("auth/oauth")
    suspend fun oauth(@Body request: Map<String, String>): AuthResponse
}
