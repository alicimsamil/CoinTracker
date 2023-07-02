package com.alicimsamil.cointracker.feature.listing.repository

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.cointracker.core.network.model.CoinMarketData

class FakePagingSource : PagingSource<Int, CoinMarketData>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinMarketData> {
        if(params.key == null){
            val firstPage = listOf<CoinMarketData>()
            val nextKey  = 2
            return LoadResult.Page(
                data = firstPage ,
                prevKey = null,
                nextKey = nextKey
            )
        } else{
            val newPage = listOf<CoinMarketData>()
            val nextKey  = 2
            return LoadResult.Page(
                data = newPage ,
                prevKey = null,
                nextKey = nextKey
            )
        }
    }

    override fun getRefreshKey(state: PagingState<Int, CoinMarketData>): Int {
        return 0
    }
}