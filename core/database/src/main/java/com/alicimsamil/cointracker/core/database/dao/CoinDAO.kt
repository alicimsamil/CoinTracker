package com.alicimsamil.cointracker.core.database.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.core.database.model.TableConstants.TABLE_NAME

@Dao
interface CoinDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addCoin(coins: List<CoinDBModel>)

    @Query("SELECT * FROM $TABLE_NAME WHERE name LIKE  '%' || :keyword || '%' OR symbol LIKE '%' || :keyword || '%'")
    fun getCoinsBySearch(keyword: String?): PagingSource<Int, CoinDBModel>

    @Query("SELECT COUNT(coinId) FROM $TABLE_NAME")
    fun getRowCount(): Int
}