package com.coffeeshop.core.location.di

import com.coffeeshop.core.location.LocationService
import com.coffeeshop.core.location.LocationServiceImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class LocationModule {

    @Binds
    @Singleton
    abstract fun bindLocationService(
        locationServiceImpl: LocationServiceImpl
    ): LocationService
}
