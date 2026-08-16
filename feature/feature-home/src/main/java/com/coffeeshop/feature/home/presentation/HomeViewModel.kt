package com.coffeeshop.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.home.domain.usecase.GetBannersUseCase
import com.coffeeshop.feature.home.domain.usecase.GetProductsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getProductsUseCase: GetProductsUseCase,
    private val getBannersUseCase: GetBannersUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(HomeUiState())
    val uiState: StateFlow<HomeUiState> = _uiState.asStateFlow()

    init {
        loadData()
    }

    fun onEvent(event: HomeUiEvent) {
        when (event) {
            is HomeUiEvent.CategorySelected -> {
                _uiState.update { it.copy(selectedCategory = event.category) }
                loadProducts(event.category)
            }
            is HomeUiEvent.ProductClicked -> { /* Handled by navigation */ }
            HomeUiEvent.Refresh -> loadData()
        }
    }

    private fun loadData() {
        loadBanners()
        loadProducts(_uiState.value.selectedCategory)
    }

    private fun loadBanners() {
        viewModelScope.launch {
            getBannersUseCase().collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(banners = result.data) }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(error = result.message) }
                    }
                    NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }

    private fun loadProducts(category: String) {
        val catParam = if (category == "All Coffee") null else category
        viewModelScope.launch {
            getProductsUseCase(catParam).collect { result ->
                when (result) {
                    is NetworkResult.Success -> {
                        _uiState.update { it.copy(products = result.data, isLoading = false) }
                    }
                    is NetworkResult.Error -> {
                        _uiState.update { it.copy(error = result.message, isLoading = false) }
                    }
                    NetworkResult.Loading -> {
                        _uiState.update { it.copy(isLoading = true) }
                    }
                }
            }
        }
    }
}
