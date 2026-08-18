package com.coffeeshop.feature.profile.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.coffeeshop.core.domain.model.ThemeMode
import com.coffeeshop.core.ui.components.button.CoffeeButton
import com.coffeeshop.core.ui.components.button.CoffeeButtonVariant
import com.coffeeshop.core.ui.components.chip.CategoryChip
import com.coffeeshop.core.ui.theme.spacing

@Composable
fun ProfileScreen(
    onNavigateToOrders: () -> Unit,
    onNavigateToFavourites: () -> Unit,
    onLogout: () -> Unit,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val spacing = MaterialTheme.spacing
    val themeMode by viewModel.themeMode.collectAsStateWithLifecycle()

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
        
        Spacer(modifier = Modifier.height(spacing.xl))

        Text(
            text = "Theme Mode",
            style = MaterialTheme.typography.titleMedium,
            color = MaterialTheme.colorScheme.onBackground
        )

        Spacer(modifier = Modifier.height(spacing.md))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(spacing.sm),
            verticalAlignment = Alignment.CenterVertically
        ) {
            ThemeMode.entries.forEach { mode ->
                val label = when (mode) {
                    ThemeMode.FOLLOW_SYSTEM -> "System"
                    ThemeMode.LIGHT -> "Light"
                    ThemeMode.DARK -> "Dark"
                }
                CategoryChip(
                    label = label,
                    isSelected = themeMode == mode,
                    onClick = { viewModel.setThemeMode(mode) },
                    modifier = Modifier.weight(1f)
                )
            }
        }

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
