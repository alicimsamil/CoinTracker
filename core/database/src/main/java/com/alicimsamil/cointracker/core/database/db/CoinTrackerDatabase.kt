package com.alicimsamil.cointracker.core.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alicimsamil.cointracker.core.database.BuildConfig
import com.alicimsamil.cointracker.core.database.dao.CoinDAO
import com.alicimsamil.cointracker.core.database.model.CoinDBModel


@Database(entities = [CoinDBModel::class], version = BuildConfig.DATABASE_VERSION_CODE)
abstract class CoinTrackerDatabase : RoomDatabase() {
    abstract fun coinDAO(): CoinDAO
}