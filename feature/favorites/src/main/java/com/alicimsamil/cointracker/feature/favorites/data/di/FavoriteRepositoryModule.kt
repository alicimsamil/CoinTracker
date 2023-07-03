package com.alicimsamil.cointracker.feature.favorites.data.di

import com.alicimsamil.cointracker.feature.favorites.data.repository.FavoritesRepositoryImpl
import com.alicimsamil.cointracker.feature.favorites.domain.repository.FavoritesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideFavoriteRepository(favoriteRepositoryImpl: FavoritesRepositoryImpl): FavoritesRepository
}