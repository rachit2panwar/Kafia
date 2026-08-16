package com.coffeeshop.feature.home.data.repository

import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.domain.model.Product
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.home.data.remote.HomeApi
import com.coffeeshop.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class HomeRepositoryImpl @Inject constructor(
    private val api: HomeApi
) : HomeRepository {

    private val dummyProducts = listOf(
        Product(
            id = "1",
            name = "Cappuccino",
            description = "with Chocolate",
            price = 250.0,
            imageUrl = "https://images.unsplash.com/photo-1572442388796-11668a67e53d?q=80&w=2070&auto=format&fit=crop",
            category = "Cappuccino",
            rating = 4.8,
            reviewCount = 230
        ),
        Product(
            id = "2",
            name = "Cappuccino",
            description = "with Oat Milk",
            price = 280.0,
            imageUrl = "https://images.unsplash.com/photo-1534778101976-62847782c213?q=80&w=1974&auto=format&fit=crop",
            category = "Cappuccino",
            rating = 4.9,
            reviewCount = 150
        ),
        Product(
            id = "3",
            name = "Moka Latte",
            description = "Deep foam, rich espresso",
            price = 220.0,
            imageUrl = "https://images.unsplash.com/photo-1541167760496-162955ed8a9f?q=80&w=1934&auto=format&fit=crop",
            category = "Latte",
            rating = 4.7,
            reviewCount = 310
        ),
        Product(
            id = "4",
            name = "Flat White",
            description = "Espresso with microfoam",
            price = 200.0,
            imageUrl = "https://images.unsplash.com/photo-1577968897966-3d4325b36b61?q=80&w=1974&auto=format&fit=crop",
            category = "All Coffee",
            rating = 4.8,
            reviewCount = 180
        )
    )

    private val dummyBanners = listOf(
        Banner(
            id = "1",
            imageUrl = "https://images.unsplash.com/photo-1501339847302-ac426a4a7cbb?q=80&w=2078&auto=format&fit=crop",
            deepLink = "home"
        )
    )

    override fun getProducts(category: String?): Flow<NetworkResult<List<Product>>> = flow {
        emit(NetworkResult.Loading)
        kotlinx.coroutines.delay(500) // Simulate network delay
        val filtered = if (category == null || category == "All Coffee") {
            dummyProducts
        } else {
            dummyProducts.filter { it.category == category }
        }
        emit(NetworkResult.Success(filtered))
    }

    override fun getBanners(): Flow<NetworkResult<List<Banner>>> = flow {
        emit(NetworkResult.Loading)
        kotlinx.coroutines.delay(500)
        emit(NetworkResult.Success(dummyBanners))
    }
}
