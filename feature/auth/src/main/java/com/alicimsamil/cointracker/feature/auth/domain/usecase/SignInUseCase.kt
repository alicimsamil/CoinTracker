package com.alicimsamil.cointracker.feature.auth.domain.usecase

import com.alicimsamil.cointracker.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class SignInUseCase @Inject constructor(private val repository: AuthRepository) {
    suspend operator fun invoke(email: String, password: String) =
        repository.signIn(email, password)
}