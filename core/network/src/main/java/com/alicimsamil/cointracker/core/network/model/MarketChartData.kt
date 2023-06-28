package com.alicimsamil.cointracker.core.network.model


import kotlinx.serialization.SerialName

data class MarketChartData(
    @SerialName("market_caps")
    val marketCaps: List<List<Double?>?>?,
    @SerialName("prices")
    val prices: List<List<Double?>?>?,
    @SerialName("total_volumes")
    val totalVolumes: List<List<Double?>?>?
)


