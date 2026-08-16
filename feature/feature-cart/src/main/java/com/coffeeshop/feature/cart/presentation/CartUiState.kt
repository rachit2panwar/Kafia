package com.coffeeshop.feature.cart.presentation

import com.coffeeshop.core.domain.model.Product

data class CartUiState(
    val isLoading: Boolean = false,
    val cartItems: List<CartItem> = emptyList(),
    val address: String = "Jl. Kpg Sutoyo",
    val deliveryFee: Double = 1.0,
    val error: String? = null
)

data class CartItem(
    val product: Product,
    val quantity: Int,
    val size: String? = null
) {
    val totalPrice: Double get() = product.price * quantity
}
