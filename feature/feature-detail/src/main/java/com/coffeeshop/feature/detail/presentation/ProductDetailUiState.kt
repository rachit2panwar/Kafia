package com.coffeeshop.feature.detail.presentation

import com.coffeeshop.core.domain.model.Product

data class ProductDetailUiState(
    val isLoading: Boolean = false,
    val product: Product? = null,
    val selectedSize: String = "M",
    val error: String? = null
)
