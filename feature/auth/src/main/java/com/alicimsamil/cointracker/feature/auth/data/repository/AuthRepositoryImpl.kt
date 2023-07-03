package com.alicimsamil.cointracker.feature.auth.data.repository

import com.alicimsamil.cointracker.core.firebase.model.AuthModel
import com.alicimsamil.cointracker.core.firebase.source.FirebaseDataSource
import com.alicimsamil.cointracker.feature.auth.domain.repository.AuthRepository
import javax.inject.Inject

class AuthRepositoryImpl @Inject constructor(private val firebaseSource: FirebaseDataSource) : AuthRepository {
    override suspend fun signIn(email: String, password: String) =
        firebaseSource.signIn(AuthModel(email, password))

    override suspend fun isLoggedIn()=
        firebaseSource.isLoggedIn()


}