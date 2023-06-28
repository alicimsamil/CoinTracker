package com.alicimsamil.cointracker.core.network.model

import kotlinx.serialization.SerialName


data class CoinMarketData (val marketData: ArrayList<CoinMarketDataItem>)

data class CoinMarketDataItem(
    @SerialName("ath")
    val ath: Double?,
    @SerialName("ath_change_percentage")
    val athChangePercentage: Double?,
    @SerialName("ath_date")
    val athDate: String?,
    @SerialName("atl")
    val atl: Double?,
    @SerialName("atl_change_percentage")
    val atlChangePercentage: Double?,
    @SerialName("atl_date")
    val atlDate: String?,
    @SerialName("circulating_supply")
    val circulatingSupply: Double?,
    @SerialName("current_price")
    val currentPrice: Double?,
    @SerialName("fully_diluted_valuation")
    val fullyDilutedValuation: Long?,
    @SerialName("high_24h")
    val high24h: Double?,
    @SerialName("id")
    val id: String?,
    @SerialName("image")
    val image: String?,
    @SerialName("last_updated")
    val lastUpdated: String?,
    @SerialName("low_24h")
    val low24h: Double?,
    @SerialName("market_cap")
    val marketCap: Long?,
    @SerialName("market_cap_change_24h")
    val marketCapChange24h: Double?,
    @SerialName("market_cap_change_percentage_24h")
    val marketCapChangePercentage24h: Double?,
    @SerialName("market_cap_rank")
    val marketCapRank: Int?,
    @SerialName("max_supply")
    val maxSupply: Double?,
    @SerialName("name")
    val name: String?,
    @SerialName("price_change_24h")
    val priceChange24h: Double?,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?,
    @SerialName("price_change_percentage_24h_in_currency")
    val priceChangePercentage24hInCurrency: Double?,
    @SerialName("sparkline_in_7d")
    val sparklineIn7d: Sparkline?,
    @SerialName("symbol")
    val symbol: String?,
    @SerialName("total_supply")
    val totalSupply: Double?,
    @SerialName("total_volume")
    val totalVolume: Long?
)

data class Sparkline(
    @SerialName("price")
    val price: List<Double?>?
)