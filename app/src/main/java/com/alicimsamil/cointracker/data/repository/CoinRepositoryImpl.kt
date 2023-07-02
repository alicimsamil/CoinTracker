package com.alicimsamil.cointracker.data.repository

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.core.network.model.CoinItem
import com.alicimsamil.cointracker.data.local.LocalDataSource
import com.alicimsamil.cointracker.data.remote.RemoteDataSource
import com.alicimsamil.cointracker.domain.repository.CoinRepository
import javax.inject.Inject

class CoinRepositoryImpl @Inject constructor(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource
) : CoinRepository {
    override suspend fun getCoins(): DataResult<List<CoinItem>?, String> =
        remoteDataSource.getCoins()

    override suspend fun addCoins(coins: List<CoinDBModel>): DataResult<Unit, String> =
        localDataSource.addCoinsToDB(coins)

}