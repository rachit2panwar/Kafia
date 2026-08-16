package com.coffeeshop.feature.home.data.mapper

import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.domain.model.Product
import com.coffeeshop.feature.home.data.remote.dto.BannerDto
import com.coffeeshop.feature.home.data.remote.dto.ProductDto

fun ProductDto.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        price = price,
        imageUrl = imageUrl,
        category = category,
        rating = rating,
        reviewCount = reviewCount,
        sizes = sizes
    )
}

fun BannerDto.toDomain(): Banner {
    return Banner(
        id = id,
        imageUrl = imageUrl,
        deepLink = deepLink
    )
}
