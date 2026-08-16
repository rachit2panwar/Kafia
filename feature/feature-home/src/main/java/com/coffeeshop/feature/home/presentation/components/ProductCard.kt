package com.coffeeshop.feature.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.coffeeshop.core.domain.model.Product

import com.coffeeshop.core.ui.theme.CoffeeBrown
import com.coffeeshop.core.ui.theme.RatingYellow
import com.coffeeshop.core.ui.theme.TextPrimary
import com.coffeeshop.core.ui.theme.TextSecondary

@Composable
fun ProductCard(
    product: Product,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .clickable { onClick() },
        shape = RoundedCornerShape(16.dp),
        color = Color.White
    ) {
        Column(modifier = Modifier.padding(10.dp)) {
            Box {
                AsyncImage(
                    model = product.imageUrl,
                    contentDescription = product.name,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(130.dp)
                        .clip(RoundedCornerShape(12.dp)),
                    contentScale = ContentScale.Crop
                )
                
                // Rating badge
                Row(
                    modifier = Modifier
                        .align(Alignment.TopStart) // Figma usually has it on top-left
                        .padding(4.dp) // Adjusted padding
                        .background(Color.Black.copy(alpha = 0.15f), RoundedCornerShape(topStart = 12.dp, bottomEnd = 12.dp))
                        .padding(horizontal = 8.dp, vertical = 2.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = RatingYellow,
                        modifier = Modifier.size(10.dp)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Text(
                        text = product.rating.toString(),
                        color = Color.White,
                        fontSize = 10.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = product.name,
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.SemiBold,
                color = TextPrimary,
                maxLines = 1
            )
            Text(
                text = product.description, // Changed from category to description as per Figma
                style = MaterialTheme.typography.bodySmall,
                color = TextSecondary,
                maxLines = 1
            )

            Spacer(modifier = Modifier.height(12.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "$ ${product.price}",
                    style = MaterialTheme.typography.titleMedium,
                    fontWeight = FontWeight.Bold,
                    color = Color(0xFF2F4B4E) // A dark teal/grey color often used for price in this design
                )
                
                Surface(
                    onClick = { /* Add to cart */ },
                    modifier = Modifier.size(32.dp),
                    color = CoffeeBrown,
                    shape = RoundedCornerShape(8.dp)
                ) {
                    Box(contentAlignment = Alignment.Center) {
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = "Add",
                            tint = Color.White,
                            modifier = Modifier.size(16.dp) // Smaller icon
                        )
                    }
                }
            }
        }
    }
}
