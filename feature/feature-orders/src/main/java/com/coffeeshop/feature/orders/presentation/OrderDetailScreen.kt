package com.coffeeshop.feature.orders.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.coffeeshop.core.ui.components.topbar.CoffeeTopBar

@Composable
fun OrderDetailScreen(
    orderId: String,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CoffeeTopBar(
                title = "Order Detail",
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = "Order Detail: $orderId",
                style = MaterialTheme.typography.headlineMedium
            )
        }
    }
}
