package com.coffeeshop.feature.home.presentation

sealed class HomeUiEvent {
    data class CategorySelected(val category: String) : HomeUiEvent()
    data class ProductClicked(val productId: String) : HomeUiEvent()
    data object Refresh : HomeUiEvent()
    data object LocationPermissionGranted : HomeUiEvent()
}
