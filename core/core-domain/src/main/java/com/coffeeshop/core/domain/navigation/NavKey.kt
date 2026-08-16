package com.coffeeshop.core.domain.navigation

import kotlinx.serialization.Serializable
import androidx.navigation3.runtime.NavKey

@Serializable
sealed interface CoffeeNavKey : NavKey {
    @Serializable
    data object Splash : CoffeeNavKey

    @Serializable
    data object Login : CoffeeNavKey

    @Serializable
    data object Home : CoffeeNavKey

    @Serializable
    data class ProductDetail(val productId: String) : CoffeeNavKey

    @Serializable
    data object Cart : CoffeeNavKey

    @Serializable
    data object Favourites : CoffeeNavKey

    @Serializable
    data object Orders : CoffeeNavKey

    @Serializable
    data object Profile : CoffeeNavKey
}
