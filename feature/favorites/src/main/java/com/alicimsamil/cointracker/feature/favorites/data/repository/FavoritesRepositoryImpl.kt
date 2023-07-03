package com.alicimsamil.cointracker.feature.favorites.data.repository

import com.alicimsamil.cointracker.core.firebase.source.FirebaseDataSource
import com.alicimsamil.cointracker.feature.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class FavoritesRepositoryImpl @Inject constructor(private val firebaseDataSource: FirebaseDataSource) :
    FavoritesRepository {
    override suspend fun getFavorites(userId: String) =
        firebaseDataSource.getFavorites(userId)

    override suspend fun getCurrentUserId() =
        firebaseDataSource.getCurrentUserId()


}