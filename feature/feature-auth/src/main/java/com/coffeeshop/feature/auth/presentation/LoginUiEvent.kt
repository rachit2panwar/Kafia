package com.coffeeshop.feature.auth.presentation

sealed class LoginUiEvent {
    data object NavigateToHome : LoginUiEvent()
    data class ShowError(val message: String) : LoginUiEvent()
}
