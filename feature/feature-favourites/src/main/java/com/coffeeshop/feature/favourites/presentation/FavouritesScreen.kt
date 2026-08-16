package com.coffeeshop.feature.favourites.presentation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.coffeeshop.core.ui.components.state.EmptyView
import com.coffeeshop.core.ui.components.topbar.CoffeeTopBar

@Composable
fun FavouritesScreen(
    onProductClick: (String) -> Unit
) {
    Scaffold(
        topBar = {
            CoffeeTopBar(title = "Favourites")
        }
    ) { innerPadding ->
        EmptyView(
            title = "No Favourites Yet",
            subtitle = "Mark your favourite coffee to see them here.",
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        )
    }
}
