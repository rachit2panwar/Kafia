package com.coffeeshop.core.data.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "cart_items")
data class CartItemEntity(
    @PrimaryKey val productId: String,
    val name: String,
    val price: Double,
    val imageUrl: String,
    val quantity: Int,
    val size: String? = null
)
