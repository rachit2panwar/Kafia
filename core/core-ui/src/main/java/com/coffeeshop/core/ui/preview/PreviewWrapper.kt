package com.coffeeshop.core.ui.preview

import androidx.compose.runtime.Composable
import com.coffeeshop.core.ui.theme.CoffeeShopTheme

/**
 * Always wrap component @Preview functions with this.
 * Never call CoffeeShopTheme directly in individual previews.
 */
@Composable
fun PreviewWrapper(content: @Composable () -> Unit) {
    CoffeeShopTheme { content() }
}
