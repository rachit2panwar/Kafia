package com.coffeeshop

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.coffeeshop.core.domain.navigation.CoffeeNavKey
import com.coffeeshop.core.ui.components.BottomNavBar
import com.coffeeshop.core.ui.components.BottomNavItem
import com.coffeeshop.feature.auth.presentation.LoginScreen
import com.coffeeshop.feature.cart.presentation.CartScreen
import com.coffeeshop.feature.detail.presentation.ProductDetailScreen
import com.coffeeshop.feature.favourites.presentation.FavouritesScreen
import com.coffeeshop.feature.home.presentation.HomeScreen
import com.coffeeshop.feature.orders.presentation.OrdersScreen
import com.coffeeshop.feature.profile.presentation.ProfileScreen

@Composable
fun CoffeeShopApp() {
    var backStack by remember { mutableStateOf(listOf<CoffeeNavKey>(CoffeeNavKey.Home)) }
    val currentKey = backStack.last()

    val showBottomBar = currentKey in listOf(
        CoffeeNavKey.Home,
        CoffeeNavKey.Favourites,
        CoffeeNavKey.Cart,
        CoffeeNavKey.Profile
    )

    val currentRoute = when (currentKey) {
        CoffeeNavKey.Home -> "home"
        CoffeeNavKey.Favourites -> "favourites"
        CoffeeNavKey.Cart -> "cart"
        CoffeeNavKey.Profile -> "profile"
        else -> ""
    }

    Scaffold(
        bottomBar = {
            if (showBottomBar) {
                BottomNavBar(
                    currentRoute = currentRoute,
                    onNavItemClick = { item ->
                        val newKey = when (item) {
                            BottomNavItem.Home -> CoffeeNavKey.Home
                            BottomNavItem.Favourites -> CoffeeNavKey.Favourites
                            BottomNavItem.Cart -> CoffeeNavKey.Cart
                            BottomNavItem.Profile -> CoffeeNavKey.Profile
                        }
                        if (currentKey != newKey) {
                            backStack = listOf(newKey)
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            backStack = backStack,
            onBack = { if (backStack.size > 1) backStack = backStack.dropLast(1) },
            entryProvider = { key ->
                when (key) {
                    CoffeeNavKey.Splash -> NavEntry(key) { /* Implement Splash */ }
                    CoffeeNavKey.Login -> NavEntry(key) { LoginScreen(onLoginSuccess = { backStack = listOf(CoffeeNavKey.Home) }) }
                    CoffeeNavKey.Home -> NavEntry(key) { HomeScreen(onProductClick = { productId -> backStack = backStack + CoffeeNavKey.ProductDetail(productId) }) }
                    is CoffeeNavKey.ProductDetail -> NavEntry(key) { ProductDetailScreen(productId = key.productId, onBack = { backStack = backStack.dropLast(1) }) }
                    CoffeeNavKey.Cart -> NavEntry(key) { CartScreen() }
                    CoffeeNavKey.Favourites -> NavEntry(key) { FavouritesScreen() }
                    CoffeeNavKey.Orders -> NavEntry(key) { OrdersScreen() }
                    CoffeeNavKey.Profile -> NavEntry(key) { ProfileScreen(onLogout = { backStack = listOf(CoffeeNavKey.Login) }) }
                    else -> error("Unknown key: $key")
                }
            }
        )
    }
}
