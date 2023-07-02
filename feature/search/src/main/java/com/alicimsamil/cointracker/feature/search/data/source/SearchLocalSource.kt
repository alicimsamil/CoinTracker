package com.alicimsamil.cointracker.feature.search.data.source

import com.alicimsamil.cointracker.core.database.dao.CoinDAO
import com.alicimsamil.cointracker.core.database.util.BaseLocalDataSource
import javax.inject.Inject

class SearchLocalSource @Inject constructor(private val coinDAO: CoinDAO) : BaseLocalDataSource() {

    suspend fun searchCoin(keyword: String?) = performDatabaseOperation {
        coinDAO.getCoinsBySearch(keyword)
    }

}