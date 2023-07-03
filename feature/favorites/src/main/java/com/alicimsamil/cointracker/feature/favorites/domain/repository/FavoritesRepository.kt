package com.alicimsamil.cointracker.feature.favorites.domain.repository

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.firebase.model.FavoriteModel

interface FavoritesRepository {
    suspend fun getFavorites(userId: String): DataResult<List<FavoriteModel>, String>

    suspend fun getCurrentUserId(): String?
}