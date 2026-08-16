package com.coffeeshop.feature.auth.domain.usecase

import com.coffeeshop.core.domain.model.User
import com.coffeeshop.core.domain.util.NetworkResult
import com.coffeeshop.core.domain.repository.AuthRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(
    private val repository: AuthRepository
) {
    suspend operator fun invoke(email: String, password: String): NetworkResult<User> {
        return repository.login(email, password)
    }
}
