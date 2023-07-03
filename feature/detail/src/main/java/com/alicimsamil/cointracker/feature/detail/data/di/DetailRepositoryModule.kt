package com.alicimsamil.cointracker.feature.detail.data.di

import com.alicimsamil.cointracker.feature.detail.data.repository.DetailRepositoryImpl
import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DetailRepositoryModule {
    @Binds
    @Singleton
    abstract fun provideDetailRepository(detailRepositoryImpl: DetailRepositoryImpl): DetailRepository
}