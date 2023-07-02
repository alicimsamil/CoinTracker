package com.alicimsamil.cointracker.data.remote

import com.alicimsamil.cointracker.core.network.service.CoinService
import com.alicimsamil.cointracker.core.network.util.BaseRemoteDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val service: CoinService) : BaseRemoteDataSource() {
    suspend fun getCoins() = performApiCall {
        service.getAllCoins()
    }
}