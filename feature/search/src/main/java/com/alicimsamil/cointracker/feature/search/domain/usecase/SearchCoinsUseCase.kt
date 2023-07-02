package com.alicimsamil.cointracker.feature.search.domain.usecase

import com.alicimsamil.cointracker.feature.search.domain.repository.SearchRepository
import javax.inject.Inject

class SearchCoinsUseCase @Inject constructor(private val repository: SearchRepository) {
    suspend operator fun invoke(keyword: String?) = repository.searchCoin(keyword)
}