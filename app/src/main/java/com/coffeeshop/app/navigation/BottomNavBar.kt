package com.coffeeshop.app.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.coffeeshop.core.navigation.Screen
import com.coffeeshop.core.navigation.navigateToTab

data class BottomNavItem(
    val label: String,
    val route: String,
    val icon: ImageVector
)

val bottomNavItems = listOf(
    BottomNavItem("Home",       Screen.Home.route,       Icons.Default.Home),
    BottomNavItem("Favourites", Screen.Favourites.route, Icons.Default.Favorite),
    BottomNavItem("Cart",       Screen.Cart.route,       Icons.Default.ShoppingCart),
    BottomNavItem("Profile",    Screen.Profile.route,    Icons.Default.Person)
)

// Screens that should SHOW the bottom nav bar
val bottomNavRoutes = setOf(
    Screen.Home.route,
    Screen.Favourites.route,
    Screen.Cart.route,
    Screen.Profile.route
)

@Composable
fun BottomNavBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    NavigationBar {
        bottomNavItems.forEach { item ->
            NavigationBarItem(
                selected  = currentRoute == item.route,
                onClick   = { navController.navigateToTab(item.route) },
                icon      = { Icon(imageVector = item.icon, contentDescription = item.label) },
                label     = { Text(item.label) }
            )
        }
    }
}
