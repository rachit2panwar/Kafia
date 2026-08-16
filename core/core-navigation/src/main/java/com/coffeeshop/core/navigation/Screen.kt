package com.coffeeshop.core.navigation

/**
 * Single source of truth for all navigation destinations.
 * All feature modules and app/ reference ONLY this file for routes.
 * Never hardcode route strings anywhere else in the project.
 */
sealed class Screen(val route: String) {

    // ── Auth flow ──────────────────────────────────────────────────────────
    data object Splash : Screen("splash")
    data object Login  : Screen("login")

    // ── Bottom nav tabs ────────────────────────────────────────────────────
    data object Home        : Screen("home")
    data object Favourites  : Screen("favourites")
    data object Cart        : Screen("cart")
    data object Profile     : Screen("profile")

    // ── Nested / secondary screens ─────────────────────────────────────────
    data object Orders : Screen("orders")   // reached from Profile tab

    // ── Screens with arguments ─────────────────────────────────────────────

    /**
     * Product detail screen.
     * Usage (navigating):   navController.navigate(Screen.ProductDetail.createRoute(productId))
     * Usage (destination):  route = Screen.ProductDetail.ROUTE
     * Argument extraction:  backStackEntry.arguments?.getString(Screen.ProductDetail.ARG)
     */
    data object ProductDetail : Screen("product_detail/{productId}") {
        const val ROUTE = "product_detail/{productId}"
        const val ARG   = "productId"
        fun createRoute(productId: String) = "product_detail/$productId"
    }

    /**
     * Order detail screen (from order history).
     */
    data object OrderDetail : Screen("order_detail/{orderId}") {
        const val ROUTE = "order_detail/{orderId}"
        const val ARG   = "orderId"
        fun createRoute(orderId: String) = "order_detail/$orderId"
    }
}
