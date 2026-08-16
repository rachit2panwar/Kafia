package com.coffeeshop.core.domain.model

data class Product(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val rating: Double = 0.0,
    val reviewCount: Int = 0,
    val sizes: List<String> = kotlin.collections.emptyList()
)
