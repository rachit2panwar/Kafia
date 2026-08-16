package com.coffeeshop.feature.auth.di

import com.coffeeshop.feature.auth.data.remote.AuthApi
import com.coffeeshop.feature.auth.data.repository.AuthRepositoryImpl
import com.coffeeshop.core.domain.repository.AuthRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AuthModule {

    @Provides
    @Singleton
    fun provideAuthApi(retrofit: Retrofit): AuthApi {
        return retrofit.create(AuthApi::class.java)
    }

    @Provides
    @Singleton
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository {
        return impl
    }
}
