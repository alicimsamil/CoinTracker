package com.alicimsamil.cointracker.core.network.model

import com.google.gson.annotations.SerializedName

data class CoinItem(
    @SerializedName("id")
    val id: String,
    @SerializedName("name")
    val name: String?,
    @SerializedName("symbol")
    val symbol: String?
)