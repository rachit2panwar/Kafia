package com.coffeeshop.core.ui.components.card

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.coffeeshop.core.ui.components.image.CoffeeAsyncImage
import com.coffeeshop.core.ui.theme.spacing
import com.coffeeshop.core.ui.tokens.CoffeeElevation
import android.content.res.Configuration
import androidx.compose.ui.tooling.preview.Preview
import com.coffeeshop.core.ui.preview.PreviewWrapper

@Composable
fun ProductCard(
    name: String,
    description: String,
    price: String,
    imageUrl: String,
    isFavourite: Boolean,
    onAddToCart: () -> Unit,
    onFavouriteToggle: () -> Unit,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    Card(
        modifier = modifier,
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(CoffeeElevation.medium),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Column {
            Box(modifier = Modifier.fillMaxWidth().height(160.dp)) {
                CoffeeAsyncImage(
                    imageUrl = imageUrl,
                    contentDescription = name,
                    modifier = Modifier.fillMaxSize()
                )
                
                IconButton(
                    onClick = onFavouriteToggle,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(spacing.xs)
                ) {
                    Icon(
                        imageVector = if (isFavourite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                        contentDescription = if (isFavourite) "Remove from favourites" else "Add to favourites",
                        tint = if (isFavourite) MaterialTheme.colorScheme.primary else Color.White
                    )
                }
            }

            Column(modifier = Modifier.padding(spacing.md)) {
                Text(
                    text = name,
                    style = MaterialTheme.typography.titleMedium,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodySmall,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
                
                Spacer(modifier = Modifier.height(spacing.sm))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = price,
                        style = MaterialTheme.typography.titleMedium,
                        color = MaterialTheme.colorScheme.primary
                    )
                    
                    Surface(
                        onClick = onAddToCart,
                        shape = CircleShape,
                        color = MaterialTheme.colorScheme.primary,
                        modifier = Modifier.size(36.dp)
                    ) {
                        Box(contentAlignment = Alignment.Center) {
                            Icon(
                                imageVector = Icons.Default.Add,
                                contentDescription = "Add to cart",
                                tint = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.size(20.dp)
                            )
                        }
                    }
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
private fun ProductCardPreview() {
    PreviewWrapper {
        ProductCard(
            name = "Cafe Mocha",
            description = "Deep foam, rich espresso",
            price = "₹ 220",
            imageUrl = "",
            isFavourite = false,
            onAddToCart = {},
            onFavouriteToggle = {},
            modifier = Modifier.padding(16.dp)
        )
    }
}
