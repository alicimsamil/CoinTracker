package com.alicimsamil.cointracker.feature.listing.data.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import com.alicimsamil.cointracker.feature.listing.domain.repository.CoinsRepository
import javax.inject.Inject
import com.alicimsamil.cointracker.feature.listing.data.source.CoinsPagingSource
import kotlinx.coroutines.flow.Flow

class CoinsRepositoryImpl @Inject constructor(private val pagingSource: CoinsPagingSource) :
    CoinsRepository {

    override fun getPagedCoins(): Flow<PagingData<CoinMarketData>> {
        return Pager( config = PagingConfig(pageSize = 20) ){ pagingSource }.flow
    }

}