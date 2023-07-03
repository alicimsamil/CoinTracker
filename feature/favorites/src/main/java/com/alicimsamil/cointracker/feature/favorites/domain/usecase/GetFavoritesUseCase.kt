package com.alicimsamil.cointracker.feature.favorites.domain.usecase

import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.feature.favorites.domain.repository.FavoritesRepository
import javax.inject.Inject

class GetFavoritesUseCase @Inject constructor(private val favoritesRepository: FavoritesRepository) {

    suspend operator fun invoke() =
        favoritesRepository.getFavorites(favoritesRepository.getCurrentUserId() ?: String.EMPTY)
}