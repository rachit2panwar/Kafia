package com.coffeeshop.core.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import com.coffeeshop.core.ui.theme.CoffeeBrown
import com.coffeeshop.core.ui.theme.TextSecondary

sealed class BottomNavItem(
    val route: String,
    val icon: ImageVector,
    val label: String
) {
    object Home : BottomNavItem("home", Icons.Default.Home, "Home")
    object Favourites : BottomNavItem("favourites", Icons.Default.Favorite, "Fav")
    object Cart : BottomNavItem("cart", Icons.Default.ShoppingCart, "Cart")
    object Profile : BottomNavItem("profile", Icons.Default.Person, "Profile")
}

@Composable
fun BottomNavBar(
    currentRoute: String,
    onNavItemClick: (BottomNavItem) -> Unit
) {
    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Favourites,
        BottomNavItem.Cart,
        BottomNavItem.Profile
    )

    NavigationBar(
        containerColor = MaterialTheme.colorScheme.surface,
        contentColor = CoffeeBrown
    ) {
        items.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(item.label) },
                selected = isSelected,
                onClick = { onNavItemClick(item) },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = CoffeeBrown,
                    unselectedIconColor = TextSecondary,
                    selectedTextColor = CoffeeBrown,
                    unselectedTextColor = TextSecondary,
                    indicatorColor = MaterialTheme.colorScheme.surfaceVariant
                )
            )
        }
    }
}
