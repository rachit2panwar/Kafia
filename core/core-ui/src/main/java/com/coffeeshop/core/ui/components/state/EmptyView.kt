package com.coffeeshop.core.ui.components.state

import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.coffeeshop.core.ui.components.button.CoffeeButton
import com.coffeeshop.core.ui.theme.spacing

@Composable
fun EmptyView(
    title: String,
    subtitle: String,
    illustrationRes: Int? = null,   // optional lottie / vector resource
    actionLabel: String? = null,
    onAction: (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Illustration would go here if illustrationRes is provided
        
        Text(
            text = title,
            style = MaterialTheme.typography.headlineSmall,
            textAlign = TextAlign.Center
        )
        
        Spacer(modifier = Modifier.height(spacing.sm))
        
        Text(
            text = subtitle,
            style = MaterialTheme.typography.bodyMedium,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onSurfaceVariant
        )
        
        if (actionLabel != null && onAction != null) {
            Spacer(modifier = Modifier.height(spacing.xl))
            CoffeeButton(
                text = actionLabel,
                onClick = onAction
            )
        }
    }
}
