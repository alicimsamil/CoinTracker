package com.alicimsamil.cointracker.core.network.model


import kotlinx.serialization.SerialName

data class CoinDetailModel(
    val description: Description?,
    @SerialName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerialName("id")
    val id: String?,
    @SerialName("image")
    val image: Image?,
    @SerialName("market_data")
    val marketData: MarketData?,
    @SerialName("name")
    val name: String?,
    @SerialName("symbol")
    val symbol: String?
)

data class Description(
    @SerialName("en")
    val en: String?
)

data class Image(
    @SerialName("large")
    val large: String?,
    @SerialName("small")
    val small: String?,
    @SerialName("thumb")
    val thumb: String?
)

data class MarketData(
    @SerialName("current_price")
    val currentPrice: CurrentPrice?,
    @SerialName("price_change_24h")
    val priceChange24h: Double?,
    @SerialName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?
)

data class CurrentPrice(
    @SerialName("usd")
    val usd: Double?
)