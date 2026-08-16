package com.coffeeshop.feature.cart.presentation

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil.compose.AsyncImage

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CartScreen(
    viewModel: CartViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Scaffold(
        modifier = Modifier.statusBarsPadding(),
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Order", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.SemiBold) },
                navigationIcon = {
                    IconButton(onClick = { /* onBack */ }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                }
            )
        },
        bottomBar = {
            Surface(
                shadowElevation = 8.dp,
                color = Color.White
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "Cash/Wallet", style = MaterialTheme.typography.bodyMedium)
                        }
                        Text(
                            text = "$ 5.53", 
                            style = MaterialTheme.typography.titleMedium, 
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colorScheme.primary
                        )
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    Button(
                        onClick = { /* Order */ },
                        modifier = Modifier.fillMaxWidth().height(56.dp),
                        shape = RoundedCornerShape(16.dp)
                    ) {
                        Text("Order")
                    }
                }
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(horizontal = 16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            item {
                Column {
                    Text(text = "Delivery Address", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(text = uiState.address, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.SemiBold)
                    Text(text = "Kpg. Sutoyo No. 620, Bilzen, Tanjungbalai.", style = MaterialTheme.typography.bodySmall, color = Color.Gray)
                    Spacer(modifier = Modifier.height(8.dp))
                    Row {
                        OutlinedButton(onClick = { /* Edit */ }, shape = RoundedCornerShape(12.dp)) {
                            Text("Edit Address")
                        }
                        Spacer(modifier = Modifier.width(8.dp))
                        OutlinedButton(onClick = { /* Add Note */ }, shape = RoundedCornerShape(12.dp)) {
                            Text("Add Note")
                        }
                    }
                }
            }

            item {
                HorizontalDivider()
            }

            items(uiState.cartItems) { item ->
                CartItemRow(item)
            }

            item {
                Surface(
                    color = Color.White,
                    shape = RoundedCornerShape(16.dp),
                    border = androidx.compose.foundation.BorderStroke(1.dp, Color.LightGray)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(16.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = MaterialTheme.colorScheme.primary)
                            Spacer(modifier = Modifier.width(8.dp))
                            Text(text = "1 Discount is Applied", fontWeight = FontWeight.Bold)
                        }
                        Icon(Icons.AutoMirrored.Filled.KeyboardArrowRight, contentDescription = null)
                    }
                }
            }

            item {
                Column {
                    Text(text = "Payment Summary", style = MaterialTheme.typography.titleMedium, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.height(12.dp))
                    SummaryRow("Price", "$ 4.53")
                    SummaryRow("Delivery Fee", "$ 1.0", isDiscounted = true)
                }
            }
        }
    }
}

@Composable
fun CartItemRow(item: CartItem) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AsyncImage(
            model = item.product.imageUrl,
            contentDescription = null,
            modifier = Modifier.size(54.dp).clip(RoundedCornerShape(12.dp)),
            contentScale = ContentScale.Crop
        )
        Spacer(modifier = Modifier.width(12.dp))
        Column(modifier = Modifier.weight(1f)) {
            Text(text = item.product.name, style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
            Text(text = item.product.category, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
        }
        Row(verticalAlignment = Alignment.CenterVertically) {
            IconButton(onClick = { /* - */ }) {
                Text("-", style = MaterialTheme.typography.titleLarge)
            }
            Text(text = item.quantity.toString(), style = MaterialTheme.typography.bodyLarge, fontWeight = FontWeight.Bold)
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
