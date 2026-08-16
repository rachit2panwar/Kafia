package com.coffeeshop.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.coffeeshop.core.ui.tokens.*

private val LightColorScheme = lightColorScheme(
    primary          = CoffeeBrown700,
    onPrimary        = CoffeeCard,
    primaryContainer = CoffeeBrown100,
    secondary        = CoffeeBrown300,
    background       = CoffeeCream,
    surface          = CoffeeCard,
    onBackground     = Neutral900,
    onSurface        = Neutral900,
    onSurfaceVariant = Neutral600,
    outline          = Neutral300,
    error            = ErrorRed
)

private val DarkColorScheme = darkColorScheme(
    primary          = CoffeeBrown300,
    onPrimary        = CoffeeBrown900,
    primaryContainer = CoffeeBrown700,
    secondary        = CoffeeBrown500,
    background       = CoffeeBgDark,
    surface          = CoffeeCardDark,
    onBackground     = CoffeeCard,
    onSurface        = CoffeeCard,
    onSurfaceVariant = Neutral300,
    outline          = Neutral600,
    error            = ErrorRed
)

// Expose spacing via CompositionLocal
val LocalSpacing = staticCompositionLocalOf { Spacing() }

@Composable
fun CoffeeShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as android.app.Activity).window
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
            WindowCompat.getInsetsController(window, view).isAppearanceLightNavigationBars = !darkTheme
        }
    }
    
    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography  = CoffeeTypography,
            shapes      = CoffeeShapes,
            content     = content
        )
    }
}
