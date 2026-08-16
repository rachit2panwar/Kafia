package com.coffeeshop.core.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary = CoffeeBrownDark,
    secondary = CoffeeAccentDark,
    tertiary = CoffeeLightDark,
    background = CoffeeCreamDark,
    surface = CoffeeCreamDark,
    onPrimary = CoffeeCream,
    onSecondary = CoffeeCream,
    onTertiary = TextPrimary,
    onBackground = CoffeeCream,
    onSurface = CoffeeCream,
)

private val LightColorScheme = lightColorScheme(
    primary = CoffeeBrown,
    secondary = CoffeeAccent,
    tertiary = CoffeeLight,
    background = CoffeeCream,
    surface = CoffeeCream,
    onPrimary = CoffeeCream,
    onSecondary = CoffeeCream,
    onTertiary = TextPrimary,
    onBackground = TextPrimary,
    onSurface = TextPrimary,
)

@Composable
fun CoffeeShopTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            // Let enableEdgeToEdge handle the colors.
            // We only need to control the icon appearance (light vs dark).
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = !darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}
