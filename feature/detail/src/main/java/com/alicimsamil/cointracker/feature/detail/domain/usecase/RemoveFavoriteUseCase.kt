package com.alicimsamil.cointracker.feature.detail.domain.usecase

import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class RemoveFavoriteUseCase @Inject constructor(private val repository: DetailRepository) {
    suspend operator fun invoke(coinId: String) =
        repository.removeFavorite(repository.getUserId(), coinId)
}