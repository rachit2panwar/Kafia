package com.coffeeshop.feature.home.domain.repository

import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.domain.model.Product
import com.coffeeshop.core.domain.util.NetworkResult
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getProducts(category: String? = null): Flow<NetworkResult<List<Product>>>
    fun getBanners(): Flow<NetworkResult<List<Banner>>>
}
