package com.alicimsamil.cointracker.feature.auth.domain.repository

import com.alicimsamil.cointracker.core.common.result.DataResult

interface AuthRepository {
    suspend fun signIn(email: String, password: String): DataResult<Boolean, String>

    suspend fun isLoggedIn(): Boolean
}