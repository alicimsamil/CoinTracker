package com.alicimsamil.cointracker.core.network.model


import com.google.gson.annotations.SerializedName

data class CoinDetailModel(
    val description: Description?,
    @SerializedName("hashing_algorithm")
    val hashingAlgorithm: String?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("image")
    val image: Image?,
    @SerializedName("market_data")
    val marketData: MarketData?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)

data class Description(
    @SerializedName("en")
    val en: String?
)

data class Image(
    @SerializedName("large")
    val large: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("thumb")
    val thumb: String?
)

data class MarketData(
    @SerializedName("current_price")
    val currentPrice: CurrentPrice?,
    @SerializedName("price_change_24h")
    val priceChange24h: Double?,
    @SerializedName("price_change_percentage_24h")
    val priceChangePercentage24h: Double?
)

data class CurrentPrice(
    @SerializedName("usd")
    val usd: Double?
)