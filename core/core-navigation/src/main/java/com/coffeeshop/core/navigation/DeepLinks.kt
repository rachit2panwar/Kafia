package com.coffeeshop.core.navigation

/**
 * All deep link URI patterns in one place.
 *
 * AndroidManifest.xml uses the BASE scheme/host.
 * AppNavGraph.kt uses the individual patterns via navDeepLink { uriPattern = ... }.
 *
 * External deep link examples:
 *   coffeeshop://app/product/abc123      → opens ProductDetailScreen
 *   coffeeshop://app/order/xyz789        → opens OrderDetailScreen
 *   coffeeshop://app/cart                → opens CartScreen
 */
object DeepLinks {
    private const val SCHEME = "coffeeshop"
    private const val HOST   = "app"
    const val BASE           = "$SCHEME://$HOST"

    // Deep link patterns — use these in navDeepLink { uriPattern = ... }
    const val PRODUCT_DETAIL = "$BASE/product/{${Screen.ProductDetail.ARG}}"
    const val ORDER_DETAIL   = "$BASE/order/{${Screen.OrderDetail.ARG}}"
    const val CART           = "$BASE/cart"
    const val HOME           = "$BASE/home"
}
