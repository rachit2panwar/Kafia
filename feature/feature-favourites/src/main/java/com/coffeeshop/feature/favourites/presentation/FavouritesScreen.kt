package com.coffeeshop.feature.favourites.presentation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.foundation.layout.statusBarsPadding

@Composable
fun FavouritesScreen(
    onProductClick: (String) -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .statusBarsPadding(), 
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Favourites Screen (Stub)")
    }
}
