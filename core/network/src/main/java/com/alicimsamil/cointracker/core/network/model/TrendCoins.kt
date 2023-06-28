package com.alicimsamil.cointracker.core.network.model


import kotlinx.serialization.SerialName

data class TrendCoins(
    @SerialName("coins")
    val coins: List<TrendCoin?>?
)

data class TrendCoin(
    @SerialName("item")
    val item: TrendCoinItem?
)

data class TrendCoinItem(
    @SerialName("coin_id")
    val coinId: Int?,
    @SerialName("id")
    val id: String?,
    @SerialName("large")
    val large: String?,
    @SerialName("market_cap_rank")
    val marketCapRank: Int?,
    @SerialName("name")
    val name: String?,
    @SerialName("price_btc")
    val priceBtc: Double?,
    @SerialName("score")
    val score: Int?,
    @SerialName("slug")
    val slug: String?,
    @SerialName("small")
    val small: String?,
    @SerialName("symbol")
    val symbol: String?,
    @SerialName("thumb")
    val thumb: String?
)