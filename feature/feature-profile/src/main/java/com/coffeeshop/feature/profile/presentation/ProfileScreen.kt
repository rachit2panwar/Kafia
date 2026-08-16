package com.coffeeshop.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.coffeeshop.core.ui.components.button.CoffeeButton
import com.coffeeshop.core.ui.components.button.CoffeeButtonVariant
import com.coffeeshop.core.ui.theme.spacing

@Composable
fun ProfileScreen(
    onNavigateToOrders: () -> Unit,
    onNavigateToFavourites: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val spacing = MaterialTheme.spacing
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .statusBarsPadding()
            .padding(spacing.lg),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Profile Screen", 
            style = MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.onBackground
        )
        
        Spacer(modifier = Modifier.height(spacing.xxl))
        
        CoffeeButton(
            text = "My Orders",
            onClick = onNavigateToOrders,
            modifier = Modifier.fillMaxWidth(),
            variant = CoffeeButtonVariant.Secondary
        )
        
        Spacer(modifier = Modifier.height(spacing.md))
        
        CoffeeButton(
            text = "Favourites",
            onClick = onNavigateToFavourites,
            modifier = Modifier.fillMaxWidth(),
            variant = CoffeeButtonVariant.Secondary
        )
        
        Spacer(modifier = Modifier.height(spacing.huge))
        
        CoffeeButton(
            text = "Logout",
            onClick = { viewModel.logout(onLogout) },
            modifier = Modifier.fillMaxWidth()
        )
    }
}
