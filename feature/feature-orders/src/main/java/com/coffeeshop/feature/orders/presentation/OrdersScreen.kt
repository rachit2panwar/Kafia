package com.coffeeshop.feature.orders.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coffeeshop.core.ui.components.state.EmptyView
import com.coffeeshop.core.ui.components.topbar.CoffeeTopBar

@Composable
fun OrdersScreen(
    onOrderClick: (String) -> Unit,
    onNavigateBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CoffeeTopBar(
                title = "My Orders",
                onNavigateBack = onNavigateBack
            )
        }
    ) { innerPadding ->
        EmptyView(
            title = "No Orders Yet",
            subtitle = "Your order history will appear here once you place an order.",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
