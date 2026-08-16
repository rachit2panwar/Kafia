package com.coffeeshop.feature.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.coffeeshop.core.domain.model.Banner

@Composable
fun BannerCarousel(
    banners: List<Banner>,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = 30.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(banners) { banner ->
            AsyncImage(
                model = banner.imageUrl,
                contentDescription = null,
                modifier = Modifier
                    .width(320.dp)
                    .height(140.dp)
                    .clip(RoundedCornerShape(16.dp)),
                contentScale = ContentScale.Crop
            )
        }
    }
}
