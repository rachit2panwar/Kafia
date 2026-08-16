package com.coffeeshop.core.domain.model

data class Order(
    val id: String,
    val items: List<OrderItem>,
    val totalPrice: Double,
    val status: String,
    val createdAt: Long
)

data class OrderItem(
    val productId: String,
    val productName: String,
    val quantity: Int,
    val price: Double,
    val size: String? = null
)
