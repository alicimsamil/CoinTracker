package com.alicimsamil.cointracker.feature.detail.domain.repository

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.network.model.CoinDetailModel
import com.alicimsamil.cointracker.core.network.model.MarketChartData

interface DetailRepository {

    suspend fun getCoinDetails(id: String) : DataResult<CoinDetailModel, String>

    suspend fun getChartData(id: String) :  DataResult<MarketChartData, String>

}