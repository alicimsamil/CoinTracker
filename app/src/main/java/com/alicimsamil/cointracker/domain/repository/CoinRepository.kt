package com.alicimsamil.cointracker.domain.repository

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.core.network.model.CoinItem

interface CoinRepository {

    suspend fun getCoins(): DataResult<List<CoinItem>?, String>

    suspend fun addCoins(coins: List<CoinDBModel>): DataResult<Unit, String>

}