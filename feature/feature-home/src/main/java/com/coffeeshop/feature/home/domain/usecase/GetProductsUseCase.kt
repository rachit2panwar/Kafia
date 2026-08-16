package com.coffeeshop.feature.home.domain.usecase

import com.coffeeshop.core.domain.model.Product
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetProductsUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(category: String? = null): Flow<NetworkResult<List<Product>>> {
        return repository.getProducts(category)
    }
}
