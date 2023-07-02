package com.alicimsamil.cointracker.feature.search.data.repository

import androidx.paging.PagingSource
import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.feature.search.data.source.SearchLocalSource
import com.alicimsamil.cointracker.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(private val localDataSource: SearchLocalSource) :
    SearchRepository {
    override suspend fun searchCoin(keyword: String?): DataResult<PagingSource<Int, CoinDBModel>, String> =
        localDataSource.searchCoin(keyword)
}