package com.coffeeshop.core.domain.model

data class Banner(
    val id: String,
    val imageUrl: String,
    val deepLink: String? = null
)
