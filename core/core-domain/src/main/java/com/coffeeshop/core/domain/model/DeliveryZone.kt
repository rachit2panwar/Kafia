package com.coffeeshop.core.domain.model

data class DeliveryZone(
    val isActive: Boolean,
    val message: String,
    val zoneId: String? = null
)
