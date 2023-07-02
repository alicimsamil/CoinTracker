package com.alicimsamil.cointracker.feature.listing.domain.usecase

import androidx.paging.map
import com.alicimsamil.cointracker.feature.listing.domain.repository.CoinsRepository
import com.alicimsamil.cointracker.feature.listing.presentation.screen.ListingModel
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetCoinsFromRemoteUseCase @Inject constructor(private val repository: CoinsRepository) {

    operator fun invoke() = repository.getPagedCoins().map {
        it.map { data ->
            ListingModel(
                id = data.id,
                name = data.name,
                symbol = data.symbol?.uppercase(),
                price = "$${String.format("%.2f", data.currentPrice)}",
                percentage = "%${String.format("%.2f", data.priceChangePercentage24h)}",
                percentageDouble = data.priceChangePercentage24h,
                sparkline = get4hBy7DayData(data.sparklineIn7d?.price),
                icon = data.image
            )
        }
    }

    /***
     * This function gets 4h data from 7 day data. Cause this data shows in small site.
     */
    fun get4hBy7DayData(prices: List<Double>?): List<Double> {
        val newList = mutableListOf<Double>()
        if (prices != null) {
            for (i in prices.indices step 4)
                newList.add(prices[i])
        }
        return newList
    }
}


