package com.alicimsamil.cointracker.core.network.source

import com.alicimsamil.cointracker.core.network.service.CoinService
import com.alicimsamil.cointracker.core.network.util.BaseRemoteDataSource
import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val coinService: CoinService) :
    BaseRemoteDataSource() {

    suspend fun getCoinDetails(coinId: String) =
        performApiCall {
            coinService.getCoinDetails(coinId = coinId)
        }


    suspend fun getTrendCoins() =
        performApiCall {
            coinService.getTrendCoins()
        }

}