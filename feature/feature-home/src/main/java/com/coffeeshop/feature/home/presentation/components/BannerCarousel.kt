package com.coffeeshop.feature.home.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
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
    if (banners.isEmpty()) return

    val spacing = MaterialTheme.spacing
    val pagerState = rememberPagerState(pageCount = { banners.size })

    HorizontalPager(
        state = pagerState,
        modifier = modifier.fillMaxWidth(),
        contentPadding = PaddingValues(horizontal = spacing.xl),
        pageSpacing = spacing.md
    ) { page ->
        val banner = banners[page]
        CoffeeAsyncImage(
            imageUrl = banner.imageUrl,
            contentDescription = null,
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .clip(MaterialTheme.shapes.large),
            contentScale = ContentScale.Crop
        )
    }
}
