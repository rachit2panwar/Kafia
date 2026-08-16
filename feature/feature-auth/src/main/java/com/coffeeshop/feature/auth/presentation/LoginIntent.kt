package com.coffeeshop.feature.auth.presentation

sealed class LoginIntent {
    data class EmailChanged(val email: String) : LoginIntent()
    data class PasswordChanged(val password: String) : LoginIntent()
    data object LoginClicked : LoginIntent()
    data object GoogleLoginClicked : LoginIntent()
}
