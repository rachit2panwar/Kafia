package com.coffeeshop.core.location

import kotlinx.coroutines.flow.Flow

interface LocationService {
    fun getCurrentLocation(): Flow<UserLocation?>
}
