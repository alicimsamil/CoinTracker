package com.alicimsamil.cointracker.feature.detail.data.source

import com.alicimsamil.cointracker.core.network.service.CoinService
import com.alicimsamil.cointracker.core.network.util.BaseRemoteDataSource
import javax.inject.Inject

class DetailRemoteSource @Inject constructor(private val service: CoinService) :
    BaseRemoteDataSource() {

    suspend fun getCoinDetail(id: String) =
        performApiCall {
            service.getCoinDetails(coinId = id)
        }


    suspend fun getChartData(id: String) =
        performApiCall {
            service.getCoinMarketChartInfo(id)
        }

}