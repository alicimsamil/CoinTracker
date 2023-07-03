package com.alicimsamil.cointracker.feature.auth.domain.usecase

import com.alicimsamil.cointracker.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class GetLoggedInInfoUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke() = repository.isLoggedIn()
}