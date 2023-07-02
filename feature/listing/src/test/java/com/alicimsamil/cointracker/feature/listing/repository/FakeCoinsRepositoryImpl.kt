package com.alicimsamil.cointracker.feature.listing.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import com.alicimsamil.cointracker.feature.listing.domain.repository.CoinsRepository
import kotlinx.coroutines.flow.Flow

class FakeCoinsRepositoryImpl : CoinsRepository {
    override fun getPagedCoins(): Flow<PagingData<CoinMarketData>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ FakePagingSource() }.flow
    }
}