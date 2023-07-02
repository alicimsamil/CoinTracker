package com.alicimsamil.cointracker.feature.listing.data.di

import com.alicimsamil.cointracker.feature.listing.data.repository.CoinsRepositoryImpl
import com.alicimsamil.cointracker.feature.listing.domain.repository.CoinsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class ListingRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideCoinsRepository(noteRepositoryImpl: CoinsRepositoryImpl): CoinsRepository
}