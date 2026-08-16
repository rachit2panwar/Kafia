package com.coffeeshop.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import com.coffeeshop.core.navigation.DeepLinks
import com.coffeeshop.core.navigation.Screen
import com.coffeeshop.core.navigation.navigateAndClearBackStack
import com.coffeeshop.feature.auth.presentation.LoginScreen
import com.coffeeshop.feature.auth.presentation.SplashScreen
import com.coffeeshop.feature.cart.presentation.CartScreen
import com.coffeeshop.feature.detail.presentation.ProductDetailScreen
import com.coffeeshop.feature.favourites.presentation.FavouritesScreen
import com.coffeeshop.feature.home.presentation.HomeScreen
import com.coffeeshop.feature.orders.presentation.OrderDetailScreen
import com.coffeeshop.feature.orders.presentation.OrdersScreen
import com.coffeeshop.feature.profile.presentation.ProfileScreen

@Composable
fun AppNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {

        // ── Splash ───────────────────────────────────────────────────────
        composable(route = Screen.Splash.route) {
            SplashScreen(
                onNavigateToHome = {
                    navController.navigateAndClearBackStack(Screen.Home.route)
                },
                onNavigateToLogin = {
                    navController.navigateAndClearBackStack(Screen.Login.route)
                }
            )
        }

        // ── Login ────────────────────────────────────────────────────────
        composable(route = Screen.Login.route) {
            LoginScreen(
                onLoginSuccess = {
                    navController.navigateAndClearBackStack(
                        route   = Screen.Home.route,
                        clearUpTo = Screen.Login.route
                    )
                }
            )
        }

        // ── Home (bottom nav root) ───────────────────────────────────────
        composable(
            route = Screen.Home.route,
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.HOME })
        ) {
            HomeScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // ── Product Detail ───────────────────────────────────────────────
        composable(
            route = Screen.ProductDetail.ROUTE,
            arguments = listOf(
                navArgument(Screen.ProductDetail.ARG) { type = NavType.StringType }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.PRODUCT_DETAIL })
        ) { backStackEntry ->
            val productId = backStackEntry.arguments
                ?.getString(Screen.ProductDetail.ARG) ?: return@composable
            ProductDetailScreen(
                productId = productId,
                onNavigateBack = { navController.popBackStack() },
                onAddToCartSuccess = { navController.navigate(Screen.Cart.route) }
            )
        }

        // ── Cart ─────────────────────────────────────────────────────────
        composable(
            route = Screen.Cart.route,
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.CART })
        ) {
            CartScreen(
                onNavigateBack = { navController.popBackStack() },
                onOrderSuccess = {
                    navController.navigate(Screen.Orders.route) {
                        popUpTo(Screen.Cart.route) { inclusive = true }
                    }
                }
            )
        }

        // ── Favourites ───────────────────────────────────────────────────
        composable(route = Screen.Favourites.route) {
            FavouritesScreen(
                onProductClick = { productId ->
                    navController.navigate(Screen.ProductDetail.createRoute(productId))
                }
            )
        }

        // ── Profile ──────────────────────────────────────────────────────
        composable(route = Screen.Profile.route) {
            ProfileScreen(
                onNavigateToOrders = { navController.navigate(Screen.Orders.route) },
                onNavigateToFavourites = { navController.navigate(Screen.Favourites.route) },
                onLogout = {
                    navController.navigateAndClearBackStack(
                        route     = Screen.Login.route,
                        clearUpTo = Screen.Home.route
                    )
                }
            )
        }

        // ── Orders ───────────────────────────────────────────────────────
        composable(route = Screen.Orders.route) {
            OrdersScreen(
                onOrderClick = { orderId ->
                    navController.navigate(Screen.OrderDetail.createRoute(orderId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        // ── Order Detail ─────────────────────────────────────────────────
        composable(
            route = Screen.OrderDetail.ROUTE,
            arguments = listOf(
                navArgument(Screen.OrderDetail.ARG) { type = NavType.StringType }
            ),
            deepLinks = listOf(navDeepLink { uriPattern = DeepLinks.ORDER_DETAIL })
        ) { backStackEntry ->
            val orderId = backStackEntry.arguments
                ?.getString(Screen.OrderDetail.ARG) ?: return@composable
            OrderDetailScreen(
                orderId = orderId,
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }
}
