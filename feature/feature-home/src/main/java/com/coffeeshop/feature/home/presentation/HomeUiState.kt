package com.coffeeshop.feature.home.presentation

import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.domain.model.Product

data class HomeUiState(
    val isLoading: Boolean = false,
    val banners: List<Banner> = emptyList(),
    val products: List<Product> = emptyList(),
    val categories: List<String> = listOf("All Coffee", "Machiato", "Latte", "Americano"),
    val selectedCategory: String = "All Coffee",
    val error: String? = null
)
