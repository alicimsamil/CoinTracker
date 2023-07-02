package com.alicimsamil.cointracker.feature.listing.domain.repository

import androidx.paging.PagingData
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import kotlinx.coroutines.flow.Flow

interface CoinsRepository {
    fun getPagedCoins() : Flow<PagingData<CoinMarketData>>
}