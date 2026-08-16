package com.coffeeshop.feature.home.data.remote.dto

data class ProductDto(
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,
    val rating: Double,
    val reviewCount: Int,
    val sizes: List<String>
)
