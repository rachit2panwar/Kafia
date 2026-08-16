package com.coffeeshop.feature.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.ui.components.image.CoffeeAsyncImage
import com.coffeeshop.core.ui.theme.spacing

@Composable
fun BannerCarousel(
    banners: List<Banner>,
    modifier: Modifier = Modifier
) {
    val spacing = MaterialTheme.spacing
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = spacing.xl),
        horizontalArrangement = Arrangement.spacedBy(spacing.md)
    ) {
        items(banners) { banner ->
            CoffeeAsyncImage(
                imageUrl = banner.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(320.dp)
                    .height(140.dp)
                    .clip(MaterialTheme.shapes.large),
                contentScale = ContentScale.Crop
            )
        }
    }
}
