package com.coffeeshop.feature.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coffeeshop.core.ui.components.button.CoffeeButton
import com.coffeeshop.core.ui.components.image.CoffeeAsyncImage
import com.coffeeshop.core.ui.components.topbar.CoffeeTopBar
import com.coffeeshop.core.ui.theme.spacing

@Composable
fun CartScreen(
    onNavigateBack: () -> Unit,
    onOrderSuccess: () -> Unit,
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    val spacing = MaterialTheme.spacing

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            CoffeeTopBar(
                title = "Order",
                onNavigateBack = onNavigateBack
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = spacing.sm,
                color = MaterialTheme.colorScheme.surface
            ) {
                Column(modifier = Modifier.padding(spacing.lg)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn, 
                                contentDescription = null, 
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(spacing.sm))
                            Text(text = "Cash/Wallet", style = MaterialTheme.typography.bodyMedium)
                        }
                        Text(
                            text = "₹ 5.53", 
                            style = MaterialTheme.typography.titleMedium, 
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(spacing.lg))
                    CoffeeButton(
                        text = "Order",
                        onClick = onOrderSuccess,
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = spacing.lg),
            verticalArrangement = Arrangement.spacedBy(spacing.lg)
        ) {
            item {
                Column {
                    Text(
                        text = "Delivery Address", 
                        style = MaterialTheme.typography.titleMedium, 
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Text(
                        text = uiState.address, 
                        style = MaterialTheme.typography.bodyLarge, 
                        fontWeight = FontWeight.SemiBold
                    )
                    Text(
                        text = "Kpg. Sutoyo No. 620, Bilzen, Tanjungbalai.", 
                        style = MaterialTheme.typography.bodySmall, 
                        color = MaterialTheme.colorScheme.onSurfaceVariant
                    )
                    Spacer(modifier = Modifier.height(spacing.sm))
                    Row {
                        OutlinedButton(
                            onClick = { /* Edit */ }, 
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text("Edit Address")
                        }
                        Spacer(modifier = Modifier.width(spacing.sm))
                        OutlinedButton(
                            onClick = { /* Add Note */ }, 
                            shape = MaterialTheme.shapes.medium
                        ) {
                            Text("Add Note")
                        }
                    }
                }
            }

            item {
                HorizontalDivider(color = MaterialTheme.colorScheme.outline.copy(alpha = 0.5f))
            }

            items(uiState.cartItems) { item ->
                CartItemRow(item)
            }

            item {
                Surface(
                    color = MaterialTheme.colorScheme.surface,
                    shape = MaterialTheme.shapes.large,
                    border = androidx.compose.foundation.BorderStroke(1.dp, MaterialTheme.colorScheme.outline)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(spacing.lg),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(
                                imageVector = Icons.Default.LocationOn, 
                                contentDescription = null, 
                                tint = MaterialTheme.colorScheme.primary
                            )
                            Spacer(modifier = Modifier.width(spacing.sm))
                            Text(text = "1 Discount is Applied", fontWeight = FontWeight.Bold)
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                    }
                }
            }

            item {
                Column {
                    Text(
                        text = "Payment Summary", 
                        style = MaterialTheme.typography.titleMedium, 
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(spacing.md))
                    SummaryRow("Price", "₹ 4.53")
                    SummaryRow("Delivery Fee", "₹ 1.0", isDiscounted = true)
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    val spacing = MaterialTheme.spacing
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        CoffeeAsyncImage(
            imageUrl = item.product.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(54.dp).clip(MaterialTheme.shapes.medium),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(spacing.md))
        Column(modifier = Modifier.weight(1f)) {
            Text(
                text = item.product.name, 
                style = MaterialTheme.typography.bodyLarge, 
                fontWeight = FontWeight.Bold
            )
            Text(
                text = item.product.category, 
                style = MaterialTheme.typography.bodySmall, 
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* - */ }) {
                Text("-", style = MaterialTheme.typography.titleLarge)
            }
            Text(
                text = item.quantity.toString(), 
                style = MaterialTheme.typography.bodyLarge, 
                fontWeight = FontWeight.Bold
            )
            IconButton(onClick = { /* + */ }) {
                Icon(Icons.Default.Add, contentDescription = null)
            }
        }
    }
}

@Composable
fun SummaryRow(label: String, value: String, isDiscounted: Boolean = false) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = label, style = MaterialTheme.typography.bodyMedium)
        Text(
            text = value, 
            style = MaterialTheme.typography.bodyMedium, 
            fontWeight = FontWeight.Bold,
            textDecoration = if (isDiscounted) androidx.compose.ui.text.style.TextDecoration.LineThrough else null
        )
    }
}
