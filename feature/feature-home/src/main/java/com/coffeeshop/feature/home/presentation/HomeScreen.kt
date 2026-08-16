package com.coffeeshop.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coffeeshop.feature.home.presentation.components.BannerCarousel
import com.coffeeshop.feature.home.presentation.components.CategoryFilterRow
import com.coffeeshop.feature.home.presentation.components.ProductCard

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import com.coffeeshop.core.ui.theme.CoffeeBrown
import com.coffeeshop.core.ui.theme.SearchBarBg

@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFFF9F9F9))
    ) {
        Box {
            // Header with Location & Search
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(280.dp)
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF131313), Color(0xFF313131))
                        )
                    )
                    .statusBarsPadding()
                    .padding(horizontal = 30.dp, vertical = 20.dp)
            ) {
                Text(
                    text = "Location",
                    color = Color(0xFFB7B7B7),
                    style = MaterialTheme.typography.bodySmall
                )
                Text(
                    text = "Bilzen, Tanjungbalai",
                    color = Color(0xFFDDDDDD),
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.SemiBold
                )

                Spacer(modifier = Modifier.height(24.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    TextField(
                        value = "",
                        onValueChange = {},
                        placeholder = { Text("Search coffee", color = Color(0xFF989898)) },
                        leadingIcon = {
                            Icon(
                                Icons.Default.Search,
                                contentDescription = null,
                                tint = Color.White
                            )
                        },
                        modifier = Modifier
                            .weight(1f)
                            .height(52.dp),
                        colors = TextFieldDefaults.colors(
                            unfocusedContainerColor = SearchBarBg,
                            focusedContainerColor = SearchBarBg,
                            unfocusedIndicatorColor = Color.Transparent,
                            focusedIndicatorColor = Color.Transparent
                        ),
                        shape = RoundedCornerShape(16.dp)
                    )
                    
                    Spacer(modifier = Modifier.width(12.dp))
                    
                    Surface(
                        modifier = Modifier.size(52.dp),
                        color = CoffeeBrown,
                        shape = RoundedCornerShape(12.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.AutoMirrored.Filled.List,
                                contentDescription = "Filter",
                                tint = Color.White
                            )
                        }
                    }
                }
            }

            // Banners positioned to overlap the header
            Column(
                modifier = Modifier
                    .padding(top = 210.dp)
                    .fillMaxWidth()
            ) {
                BannerCarousel(banners = uiState.banners)
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Categories
        CategoryFilterRow(
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = { viewModel.onEvent(HomeUiEvent.CategorySelected(it)) }
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Products Grid
        if (uiState.isLoading) {
            Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                CircularProgressIndicator()
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = 30.dp, vertical = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        product = product,
                        onClick = { onProductClick(product.id) },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}
