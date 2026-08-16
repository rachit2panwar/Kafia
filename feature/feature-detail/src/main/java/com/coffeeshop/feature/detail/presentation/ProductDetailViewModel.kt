package com.coffeeshop.feature.detail.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.coffeeshop.core.domain.util.NetworkResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProductDetailViewModel @Inject constructor(
    // private val getProductUseCase: GetProductUseCase
) : ViewModel() {

    private val _uiState = MutableStateFlow(ProductDetailUiState())
    val uiState: StateFlow<ProductDetailUiState> = _uiState.asStateFlow()

    fun loadProduct(productId: String) {
        // Mocking for now as UseCase is not implemented
        _uiState.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            // result = getProductUseCase(productId)
            _uiState.update { it.copy(isLoading = false) }
        }
    }

    fun onSizeSelected(size: String) {
        _uiState.update { it.copy(selectedSize = size) }
    }
}
