package com.coffeeshop.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.auth.domain.usecase.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(LoginUiState())
    val uiState: StateFlow<LoginUiState> = _uiState.asStateFlow()

    fun onEvent(event: LoginUiEvent) {
        when (event) {
            is LoginUiEvent.EmailChanged -> {
                _uiState.update { it.copy(email = event.email) }
            }
            is LoginUiEvent.PasswordChanged -> {
                _uiState.update { it.copy(password = event.password) }
            }
            LoginUiEvent.LoginClicked -> login()
            LoginUiEvent.GoogleLoginClicked -> loginWithGoogle()
        }
    }

    private fun login() {
        val email = _uiState.value.email
        val password = _uiState.value.password

        if (email.isBlank() || password.isBlank()) {
            _uiState.update { it.copy(error = "Please fill all fields") }
            return
        }

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, error = null) }
            when (val result = loginUseCase(email, password)) {
                is NetworkResult.Success -> {
                    _uiState.update { it.copy(isLoading = false, isLoginSuccess = true) }
                }
                is NetworkResult.Error -> {
                    _uiState.update { it.copy(isLoading = false, error = result.message) }
                }
                NetworkResult.Loading -> {
                    _uiState.update { it.copy(isLoading = true) }
                }
            }
        }
    }

    private fun loginWithGoogle() {
        // Implement later
    }
}
