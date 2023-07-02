package com.alicimsamil.cointracker.feature.listing.data.source

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.alicimsamil.cointracker.core.network.model.CoinMarketData
import com.alicimsamil.cointracker.core.network.service.CoinService
import javax.inject.Inject

class CoinsPagingSource @Inject constructor(private val service: CoinService) :
    PagingSource<Int, CoinMarketData>() {

    override fun getRefreshKey(state: PagingState<Int, CoinMarketData>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, CoinMarketData> {
        return try {
            val nextPageNumber = params.key ?: 1
            val response =
                service.getPagedCoins(page = params.key ?: 1)
            LoadResult.Page(
                data = response.body()!!,
                prevKey = null,
                nextKey = nextPageNumber + 1
            )
        } catch (exception: Exception) {
            LoadResult.Error(exception)
        }
    }
}