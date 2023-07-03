package com.alicimsamil.cointracker.feature.auth.data.di

import com.alicimsamil.cointracker.feature.auth.data.repository.AuthRepositoryImpl
import com.alicimsamil.cointracker.feature.auth.domain.repository.AuthRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AuthRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideAuthRepository(detailRepositoryImpl: AuthRepositoryImpl): AuthRepository
}