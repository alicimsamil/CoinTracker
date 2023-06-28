package com.alicimsamil.cointracker.core.network.model

import kotlinx.serialization.SerialName

data class Coins(val coins: List<CoinItem>? = null)

data class CoinItem(
    @SerialName("id")
    val id: String?,
    @SerialName("name")
    val name: String?,
    @SerialName("symbol")
    val symbol: String?
)