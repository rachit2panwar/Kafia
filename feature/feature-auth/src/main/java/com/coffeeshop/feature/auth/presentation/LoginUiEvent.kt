package com.coffeeshop.feature.auth.presentation

sealed class LoginUiEvent {
    data class EmailChanged(val email: String) : LoginUiEvent()
    data class PasswordChanged(val password: String) : LoginUiEvent()
    object LoginClicked : LoginUiEvent()
    object GoogleLoginClicked : LoginUiEvent()
}
