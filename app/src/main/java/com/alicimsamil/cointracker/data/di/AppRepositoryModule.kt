package com.alicimsamil.cointracker.data.di

import com.alicimsamil.cointracker.data.repository.CoinRepositoryImpl
import com.alicimsamil.cointracker.domain.repository.CoinRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class AppRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideCoinRepository(coinRepositoryImpl: CoinRepositoryImpl): CoinRepository
}