package com.alicimsamil.cointracker.data.local

import com.alicimsamil.cointracker.core.database.dao.CoinDAO
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.core.database.util.BaseLocalDataSource
import javax.inject.Inject

class LocalDataSource @Inject constructor(private val dao: CoinDAO) : BaseLocalDataSource() {

    suspend fun addCoinsToDB(coins: List<CoinDBModel>) = performDatabaseOperation {
        dao.addCoin(coins)
    }

}