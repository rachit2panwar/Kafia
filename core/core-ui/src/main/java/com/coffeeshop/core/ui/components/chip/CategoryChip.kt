package com.coffeeshop.core.ui.components.chip

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import com.coffeeshop.core.ui.theme.spacing
import com.coffeeshop.core.ui.tokens.CoffeeMotion

@Composable
fun CategoryChip(
    label: String,
    isSelected: Boolean,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val backgroundColor by animateColorAsState(
        targetValue = if (isSelected) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primaryContainer,
        animationSpec = tween(durationMillis = CoffeeMotion.durationShort),
        label = "chip_bg"
    )

    val textColor by animateColorAsState(
        targetValue = if (isSelected) Color.White else MaterialTheme.colorScheme.primary,
        animationSpec = tween(durationMillis = CoffeeMotion.durationShort),
        label = "chip_text"
    )

    val spacing = MaterialTheme.spacing

    Box(
        modifier = modifier
            .clip(MaterialTheme.shapes.extraLarge)
            .background(backgroundColor)
            .clickable(onClick = onClick)
            .padding(horizontal = spacing.lg, vertical = spacing.sm),
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = label,
            color = textColor,
            style = MaterialTheme.typography.labelMedium
        )
    }
}
