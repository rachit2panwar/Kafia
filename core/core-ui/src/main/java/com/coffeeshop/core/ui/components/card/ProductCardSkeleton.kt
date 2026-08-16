package com.coffeeshop.core.ui.components.card

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.coffeeshop.core.ui.components.loader.ShimmerBox
import com.coffeeshop.core.ui.theme.spacing
import com.coffeeshop.core.ui.tokens.CoffeeElevation

@Composable
fun ProductCardSkeleton(modifier: Modifier = Modifier) {
    val spacing = MaterialTheme.spacing
    Card(
        modifier  = modifier,
        shape     = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(CoffeeElevation.medium)
    ) {
        Column {
            // Image area
            ShimmerBox(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(160.dp)
            )
            Column(modifier = Modifier.padding(spacing.md)) {
                // Product name
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.72f)
                        .height(16.dp)
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(Modifier.height(spacing.xs))
                // Description
                ShimmerBox(
                    modifier = Modifier
                        .fillMaxWidth(0.55f)
                        .height(13.dp)
                        .clip(MaterialTheme.shapes.extraSmall)
                )
                Spacer(Modifier.height(spacing.sm))
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    // Price
                    ShimmerBox(
                        modifier = Modifier
                            .width(56.dp)
                            .height(16.dp)
                            .clip(MaterialTheme.shapes.extraSmall)
                    )
                    // "+" button circle
                    ShimmerBox(
                        modifier = Modifier
                            .size(36.dp)
                            .clip(CircleShape)
                    )
                }
            }
        }
    }
}
