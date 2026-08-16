package com.coffeeshop

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation3.runtime.NavEntry
import androidx.navigation3.ui.NavDisplay
import com.coffeeshop.core.domain.navigation.CoffeeNavKey
import com.coffeeshop.core.ui.components.BottomNavBar
import com.coffeeshop.core.ui.components.BottomNavItem
import com.coffeeshop.core.ui.theme.CoffeeShopTheme
import com.coffeeshop.feature.auth.presentation.LoginScreen
import com.coffeeshop.feature.cart.presentation.CartScreen
import com.coffeeshop.feature.detail.presentation.ProductDetailScreen
import com.coffeeshop.feature.favourites.presentation.FavouritesScreen
import com.coffeeshop.feature.home.presentation.HomeScreen
import com.coffeeshop.feature.home.presentation.HomeScreenContent
import com.coffeeshop.feature.home.presentation.HomeUiState
import com.coffeeshop.feature.orders.presentation.OrdersScreen
import com.coffeeshop.feature.profile.presentation.ProfileScreen

@Composable
fun CoffeeShopApp() {
    var backStack by remember { mutableStateOf(listOf<CoffeeNavKey>(CoffeeNavKey.Home)) }
    CoffeeShopApp(
        backStack = backStack,
        onBackStackChange = { backStack = it },
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

@Composable
fun CoffeeShopApp(
    backStack: List<CoffeeNavKey>,
    onBackStackChange: (List<CoffeeNavKey>) -> Unit,
    entryProvider: (CoffeeNavKey) -> NavEntry<CoffeeNavKey>
) {
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
                            onBackStackChange(listOf(newKey))
                        }
                    }
                )
            }
        }
    ) { innerPadding ->
        NavDisplay(
            modifier = Modifier.padding(bottom = innerPadding.calculateBottomPadding()),
            backStack = backStack,
            onBack = { if (backStack.size > 1) onBackStackChange(backStack.dropLast(1)) },
            entryProvider = entryProvider
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CoffeeShopAppPreview() {
    CoffeeShopTheme {
        CoffeeShopApp(
            backStack = listOf(CoffeeNavKey.Home),
            onBackStackChange = {},
            entryProvider = { key ->
                when (key) {
                    CoffeeNavKey.Home -> NavEntry(key) {
                        HomeScreenContent(
                            uiState = HomeUiState(),
                            onProductClick = {},
                            onCategorySelected = {}
                        )
                    }
                    else -> NavEntry(key) {
                        Box(
                            modifier = Modifier.fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Screen: $key")
                        }
                    }
                }
            }
        )
    }
}
