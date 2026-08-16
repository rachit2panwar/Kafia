package com.coffeeshop.core.ui.components.button

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun CoffeeButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isLoading: Boolean = false,
    variant: CoffeeButtonVariant = CoffeeButtonVariant.Primary
) {
    val colors = when (variant) {
        CoffeeButtonVariant.Primary -> ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
        CoffeeButtonVariant.Secondary -> ButtonDefaults.outlinedButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
        CoffeeButtonVariant.Ghost -> ButtonDefaults.textButtonColors(
            contentColor = MaterialTheme.colorScheme.primary
        )
    }

    val shape = MaterialTheme.shapes.extraLarge

    when (variant) {
        CoffeeButtonVariant.Primary -> {
            Button(
                onClick = onClick,
                modifier = modifier.height(56.dp),
                enabled = enabled && !isLoading,
                colors = colors,
                shape = shape
            ) {
                ButtonContent(text = text, isLoading = isLoading, color = MaterialTheme.colorScheme.onPrimary)
            }
        }
        CoffeeButtonVariant.Secondary -> {
            OutlinedButton(
                onClick = onClick,
                modifier = modifier.height(56.dp),
                enabled = enabled && !isLoading,
                colors = colors,
                shape = shape,
                border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary)
            ) {
                ButtonContent(text = text, isLoading = isLoading, color = MaterialTheme.colorScheme.primary)
            }
        }
        CoffeeButtonVariant.Ghost -> {
            TextButton(
                onClick = onClick,
                modifier = modifier.height(56.dp),
                enabled = enabled && !isLoading,
                colors = colors,
                shape = shape
            ) {
                ButtonContent(text = text, isLoading = isLoading, color = MaterialTheme.colorScheme.primary)
            }
        }
    }
}

@Composable
private fun ButtonContent(
    text: String,
    isLoading: Boolean,
    color: Color
) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(24.dp),
            color = color,
            strokeWidth = 2.dp
        )
    } else {
        Text(
            text = text,
            style = MaterialTheme.typography.labelLarge
        )
    }
}

enum class CoffeeButtonVariant { Primary, Secondary, Ghost }
