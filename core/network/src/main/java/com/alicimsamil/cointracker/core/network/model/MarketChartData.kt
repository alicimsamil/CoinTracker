package com.alicimsamil.cointracker.core.network.model


import com.google.gson.annotations.SerializedName

data class MarketChartData(
    @SerializedName("market_caps")
    val marketCaps: List<List<Double?>?>?,
    @SerializedName("prices")
    val prices: List<List<Double?>?>?,
    @SerializedName("total_volumes")
    val totalVolumes: List<List<Double?>?>?
)


