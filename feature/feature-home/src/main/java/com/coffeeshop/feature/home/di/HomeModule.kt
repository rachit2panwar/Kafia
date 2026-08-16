package com.coffeeshop.feature.home.di

import com.coffeeshop.feature.home.data.remote.HomeApi
import com.coffeeshop.feature.home.data.repository.HomeRepositoryImpl
import com.coffeeshop.feature.home.domain.repository.HomeRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object HomeModule {

    @Provides
    @Singleton
    fun provideHomeApi(retrofit: Retrofit): HomeApi {
        return retrofit.create(HomeApi::class.java)
    }

    @Provides
    @Singleton
    fun provideHomeRepository(impl: HomeRepositoryImpl): HomeRepository {
        return impl
    }
}
