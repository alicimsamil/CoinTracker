package com.alicimsamil.cointracker.feature.detail.presentation

data class DetailModel(
    val name: String?,
    val symbol: String?,
    val isFavorite: Boolean? = false,
    val icon: String?,
    val price: String?,
    val percentage: String?,
    val percentageDouble: Double?,
    val hashingAlgorithm: String?,
    val description: String?
)
