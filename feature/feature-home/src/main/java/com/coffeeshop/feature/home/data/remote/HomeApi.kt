package com.coffeeshop.feature.home.data.remote

import com.coffeeshop.feature.home.data.remote.dto.ProductDto
import com.coffeeshop.feature.home.data.remote.dto.BannerDto
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApi {
    @GET("products")
    suspend fun getProducts(@Query("category") category: String?): List<ProductDto>

    @GET("banners")
    suspend fun getBanners(): List<BannerDto>
}
