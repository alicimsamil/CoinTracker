package com.alicimsamil.cointracker.core.network.model


import com.google.gson.annotations.SerializedName

data class TrendCoins(
    @SerializedName("coins")
    val coins: List<TrendCoin?>?
)

data class TrendCoin(
    @SerializedName("item")
    val item: TrendCoinItem?
)

data class TrendCoinItem(
    @SerializedName("coin_id")
    val coinId: Int?,
    @SerializedName("id")
    val id: String?,
    @SerializedName("large")
    val large: String?,
    @SerializedName("market_cap_rank")
    val marketCapRank: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("price_btc")
    val priceBtc: Double?,
    @SerializedName("score")
    val score: Int?,
    @SerializedName("slug")
    val slug: String?,
    @SerializedName("small")
    val small: String?,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("thumb")
    val thumb: String?
)