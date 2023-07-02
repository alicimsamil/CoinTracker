package com.alicimsamil.cointracker.feature.search.domain.repository

import androidx.paging.PagingSource
import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.database.model.CoinDBModel

interface SearchRepository {
    suspend fun searchCoin(keyword: String?): DataResult<PagingSource<Int, CoinDBModel>, String>
}