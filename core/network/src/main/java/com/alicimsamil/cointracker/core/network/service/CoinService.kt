package com.alicimsamil.cointracker.core.network.service

import com.alicimsamil.cointracker.core.network.model.CoinDetailModel
import com.alicimsamil.cointracker.core.network.model.CoinItem
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import com.alicimsamil.cointracker.core.network.model.MarketChartData
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CoinService {

    @GET("coins/markets")
    suspend fun getPagedCoins(
        @Query("vs_currency") exchangeCurrency: String = "usd",
        @Query("order") order: String = "market_cap_desc",
        @Query("per_page") per_page: Int = 20,
        @Query("page") page: Int = 1,
        @Query("sparkline") includeSparkline: Boolean = true,
        @Query("price_change_percentage") changePercentage: String = "7d"
    ) : Response<List<CoinMarketData>>

    @GET("coins/list")
    suspend fun getAllCoins() : Response<List<CoinItem>?>

    @GET("coins/{coin}")
    suspend fun getCoinDetails(
        @Path("coin") coinId: String,
        @Query("localization") localization: Boolean = false,
        @Query("tickers") tickers: Boolean = false,
        @Query("market_data") marketData: Boolean = true,
        @Query("community_data") communityData: Boolean = false,
        @Query("developer_data") developerData: Boolean = false,
        @Query("sparkline") sparkline: Boolean = false
    ) : Response<CoinDetailModel>

    @GET("coins/{coin}/market_chart")
    suspend fun getCoinMarketChartInfo(
        @Path("coin") coinId: String,
        @Query("vs_currency") vsCurrency: String = "usd",
        @Query("days") days: Int = 30,
        @Query("interval") interval: String = "daily"
    ) : Response<MarketChartData>

}