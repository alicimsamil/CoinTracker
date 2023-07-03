package com.alicimsamil.cointracker.feature.detail.data.repository

import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.firebase.source.FirebaseDataSource
import com.alicimsamil.cointracker.feature.detail.data.source.DetailRemoteSource
import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class DetailRepositoryImpl @Inject constructor(
    private val remoteSource: DetailRemoteSource,
    private val firebaseDataSource: FirebaseDataSource
) : DetailRepository {

    override suspend fun getCoinDetails(id: String) = remoteSource.getCoinDetail(id)

    override suspend fun getChartData(id: String) = remoteSource.getChartData(id)

    override suspend fun getFavorites(id: String) = firebaseDataSource.getFavoritesIds(id)

    override suspend fun getUserId() = firebaseDataSource.getCurrentUserId() ?: String.EMPTY

    override suspend fun addFavorite(
        userId: String,
        coinId: String,
        coinName: String?,
        coinPrice: String?,
        coinSymbol: String?,
        coinImage: String?
    ) = firebaseDataSource.addFavorite(userId, coinId, coinName, coinPrice, coinSymbol, coinImage)

    override suspend fun removeFavorite(
        userId: String,
        coinId: String
    ) = firebaseDataSource.removeFavorite(userId, coinId)

}