package com.alicimsamil.cointracker.core.database.di

import android.content.Context
import androidx.room.Room
import com.alicimsamil.cointracker.core.database.BuildConfig
import com.alicimsamil.cointracker.core.database.dao.CoinDAO
import com.alicimsamil.cointracker.core.database.db.CoinTrackerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
private object DatabaseModule {
    @Provides
    @Singleton
    fun provideCoinDatabase(@ApplicationContext context: Context): CoinTrackerDatabase =
        Room.databaseBuilder(context, CoinTrackerDatabase::class.java, BuildConfig.DATABASE_NAME)
            .fallbackToDestructiveMigration().build()

    @Provides
    @Singleton
    fun provideCoinDAO(coinDatabase: CoinTrackerDatabase): CoinDAO =
        coinDatabase.coinDAO()
}