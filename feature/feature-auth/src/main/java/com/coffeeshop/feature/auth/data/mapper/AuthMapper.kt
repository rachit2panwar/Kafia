package com.coffeeshop.feature.auth.data.mapper

import com.coffeeshop.core.domain.model.User
import com.coffeeshop.feature.auth.data.remote.dto.UserDto

fun UserDto.toDomain(): User {
    return User(
        id = id,
        name = name,
        email = email,
        avatarUrl = avatarUrl
    )
}
