package com.alicimsamil.cointracker.feature.listing.presentation.screen

data class ListingModel(
    val id: String?,
    val name: String?,
    val symbol: String?,
    val price: String?,
    val icon: String?,
    val percentage: String?,
    val percentageDouble: Double?,
    val sparkline: List<Double>?
)
