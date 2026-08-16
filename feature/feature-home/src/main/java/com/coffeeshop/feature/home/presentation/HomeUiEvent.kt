package com.coffeeshop.feature.home.presentation

sealed class HomeUiEvent {
    data class CategorySelected(val category: String) : HomeUiEvent()
    data class ProductClicked(val productId: String) : HomeUiEvent()
    object Refresh : HomeUiEvent()
}
