package com.coffeeshop.feature.auth.data.remote.dto

data class LoginRequest(
    val email: String,
    val password: String
)
