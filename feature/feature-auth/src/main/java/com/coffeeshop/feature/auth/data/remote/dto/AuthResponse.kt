package com.coffeeshop.feature.auth.data.remote.dto

data class AuthResponse(
    val token: String,
    val user: UserDto
)

data class UserDto(
    val id: String,
    val name: String,
    val email: String,
    val avatarUrl: String?
)
