package com.alicimsamil.cointracker.feature.detail.domain.usecase

import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import javax.inject.Inject

class AddFavoriteUseCase @Inject constructor(private val repository: DetailRepository) {
    suspend operator fun invoke(
        coinId: String,
        coinName: String?,
        coinPrice: String?,
        coinSymbol: String?,
        coinImage: String?
    ) = repository.addFavorite(
            repository.getUserId(),
            coinId,
            coinName,
            coinPrice,
            coinSymbol,
            coinImage
        )

}