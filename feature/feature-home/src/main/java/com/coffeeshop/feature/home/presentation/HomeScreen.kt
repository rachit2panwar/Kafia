package com.coffeeshop.feature.home.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coffeeshop.core.ui.components.card.ProductCard
import com.coffeeshop.core.ui.components.card.ProductCardSkeleton
import com.coffeeshop.core.ui.components.input.SearchBar
import com.coffeeshop.core.ui.theme.spacing
import com.coffeeshop.core.ui.preview.PreviewWrapper
import com.coffeeshop.feature.home.presentation.components.BannerCarousel
import com.coffeeshop.feature.home.presentation.components.CategoryFilterRow
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun HomeScreen(
    onProductClick: (String) -> Unit,
    viewModel: HomeViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    HomeScreenContent(
        uiState = uiState,
        onProductClick = onProductClick,
        onCategorySelected = { viewModel.onEvent(HomeUiEvent.CategorySelected(it)) },
        onSearchQueryChange = { /* viewModel.onSearch(it) */ },
        onSearchSubmit = { /* viewModel.onSearchSubmit() */ }
    )
}

@Composable
fun HomeScreenContent(
    uiState: HomeUiState,
    onProductClick: (String) -> Unit,
    onCategorySelected: (String) -> Unit,
    onSearchQueryChange: (String) -> Unit,
    onSearchSubmit: () -> Unit
) {
    val spacing = MaterialTheme.spacing
    
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
    ) {
        Box {
            // Header with Location & Search
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(spacing.huge * 4) // Approx 256dp
                    .background(
                        brush = Brush.verticalGradient(
                            colors = listOf(Color(0xFF131313), Color(0xFF313131))
                        )
                    )
                    .statusBarsPadding()
                    .padding(horizontal = spacing.xl, vertical = spacing.xl)
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

                Spacer(modifier = Modifier.height(spacing.xl))

                SearchBar(
                    query = "",
                    onQueryChange = onSearchQueryChange,
                    onSearchSubmit = onSearchSubmit,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            // Banners positioned to overlap the header
            Column(
                modifier = Modifier
                    .padding(top = spacing.huge * 3) // Approx 192dp
                    .fillMaxWidth()
            ) {
                BannerCarousel(banners = uiState.banners)
            }
        }

        Spacer(modifier = Modifier.height(spacing.xl))

        // Categories
        CategoryFilterRow(
            categories = uiState.categories,
            selectedCategory = uiState.selectedCategory,
            onCategorySelected = onCategorySelected
        )

        Spacer(modifier = Modifier.height(spacing.lg))

        // Products Grid
        if (uiState.isLoading) {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = spacing.xl, vertical = spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(spacing.lg),
                verticalArrangement = Arrangement.spacedBy(spacing.lg),
                modifier = Modifier.fillMaxSize()
            ) {
                items(4) {
                    ProductCardSkeleton()
                }
            }
        } else {
            LazyVerticalGrid(
                columns = GridCells.Fixed(2),
                contentPadding = PaddingValues(horizontal = spacing.xl, vertical = spacing.lg),
                horizontalArrangement = Arrangement.spacedBy(spacing.lg),
                verticalArrangement = Arrangement.spacedBy(spacing.lg),
                modifier = Modifier.fillMaxSize()
            ) {
                items(uiState.products) { product ->
                    ProductCard(
                        name = product.name,
                        description = product.description,
                        price = "₹ ${product.price}",
                        imageUrl = product.imageUrl,
                        isFavourite = false, // TODO: state
                        onAddToCart = { /* TODO */ },
                        onFavouriteToggle = { /* TODO */ },
                        modifier = Modifier.fillMaxWidth()
                    )
                }
            }
        }
    }
}

@Preview(
    name = "Light",
    showBackground = true,
    backgroundColor = 0xFFFFF8F2
)
@Preview(
    name = "Dark",
    showBackground = true,
    backgroundColor = 0xFF120C05,
    uiMode = Configuration.UI_MODE_NIGHT_YES
)
@Composable
private fun HomeScreenPreview() {
    PreviewWrapper {
        HomeScreenContent(
            uiState = HomeUiState(
                isLoading = false,
                banners = emptyList(),
                products = emptyList()
            ),
            onProductClick = {},
            onCategorySelected = {},
            onSearchQueryChange = {},
            onSearchSubmit = {}
        )
    }
}
