package com.coffeeshop.core.ui.components.loader

import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import com.coffeeshop.core.ui.tokens.CoffeeMotion
import com.coffeeshop.core.ui.tokens.ShimmerDark
import com.coffeeshop.core.ui.tokens.ShimmerLight

@Composable
fun ShimmerBox(
    modifier: Modifier = Modifier,
    isDark: Boolean = false
) {
    val baseColor      = if (isDark) ShimmerDark  else ShimmerLight
    val highlightColor = if (isDark) Color(0xFF3A3A3A) else Color(0xFFFFFFFF)

    val shimmerColors = listOf(baseColor, highlightColor, baseColor)

    val transition = rememberInfiniteTransition(label = "shimmer")
    val translateAnim by transition.animateFloat(
        initialValue = 0f,
        targetValue  = 1200f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = CoffeeMotion.durationLong * 2,
                easing         = LinearEasing
            ),
            repeatMode = RepeatMode.Restart
        ),
        label = "shimmer_x"
    )

    val brush = Brush.linearGradient(
        colors     = shimmerColors,
        start      = Offset(translateAnim - 400f, 0f),
        end        = Offset(translateAnim, 0f)
    )

    Box(modifier = modifier.background(brush))
}
