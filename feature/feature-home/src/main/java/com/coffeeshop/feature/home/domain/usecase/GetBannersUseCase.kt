package com.coffeeshop.feature.home.domain.usecase

import com.coffeeshop.core.domain.model.Banner
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.feature.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBannersUseCase @Inject constructor(
    private val repository: HomeRepository
) {
    operator fun invoke(): Flow<NetworkResult<List<Banner>>> {
        return repository.getBanners()
    }
}
