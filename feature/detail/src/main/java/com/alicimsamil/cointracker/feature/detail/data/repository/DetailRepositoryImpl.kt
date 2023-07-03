package com.alicimsamil.cointracker.feature.detail.data.repository

import com.alicimsamil.cointracker.feature.detail.data.source.DetailRemoteSource
import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(private val remoteSource: DetailRemoteSource) :
    DetailRepository {

    override suspend fun getCoinDetails(id: String) = remoteSource.getCoinDetail(id)

    override suspend fun getChartData(id: String) = remoteSource.getChartData(id)

}