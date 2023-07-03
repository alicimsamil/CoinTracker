package com.alicimsamil.cointracker.core.firebase.source

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.firebase.model.AuthModel
import com.alicimsamil.cointracker.core.firebase.model.FavoriteModel
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthException
import com.google.firebase.firestore.FieldValue
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import kotlinx.coroutines.CompletableDeferred
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirebaseDataSource @Inject constructor(
    private val firestore: FirebaseFirestore,
    private val auth: FirebaseAuth
) {

    suspend fun signIn(authModel: AuthModel): DataResult<Boolean, String> {
        val deferred = CompletableDeferred<DataResult<Boolean, String>>()
        try {
            auth.signInWithEmailAndPassword(authModel.email, authModel.password).await().user
            deferred.complete(Success(true))
        } catch (exception: FirebaseAuthException) {
            if (exception.errorCode == AUTH_ERROR) {
                auth.createUserWithEmailAndPassword(authModel.email, authModel.password)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {
                            deferred.complete(Success(true))
                        } else {
                            deferred.complete(Error(it.exception?.message))
                        }
                    }
            } else {
                deferred.complete(Error(exception.message))
            }
        }
        return deferred.await()
    }

    fun isLoggedIn(): Boolean {
        return auth.currentUser != null
    }

    fun getCurrentUserId(): String? {
        return auth.currentUser?.uid
    }

    suspend fun addFavorite(
        userId: String,
        coinId: String,
        coinName: String?,
        coinPrice: String?,
        coinSymbol: String?,
        coinImage: String?
    ): DataResult<Boolean, String> {
        val deferred = CompletableDeferred<DataResult<Boolean, String>>()
        try {
            val data = hashMapOf(
                "coinId" to coinId,
                "coinName" to coinName,
                "coinPrice" to coinPrice,
                "coinSymbol" to coinSymbol,
                "coinImage" to coinImage
            )
            firestore.collection(COLLECTION).document(userId).collection(COIN_COLLECTION)
                .document(coinId).set(data).await()
            deferred.complete(Success(true))
        } catch (exception: FirebaseFirestoreException) {
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }

    suspend fun removeFavorite(userId: String, coinId: String): DataResult<Boolean, String> {
        val deferred = CompletableDeferred<DataResult<Boolean, String>>()
        try {
            firestore.collection(COLLECTION).document(userId).collection(COIN_COLLECTION)
                .document(coinId).delete().await()
            deferred.complete(Success(true))
        } catch (exception: FirebaseFirestoreException) {
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }

    suspend fun getFavoritesIds(userId: String): DataResult<List<String>, String> {
        val deferred = CompletableDeferred<DataResult<List<String>, String>>()
        try {
            val query =
                firestore.collection(COLLECTION).document(userId).collection(COIN_COLLECTION).get()
                    .await().documents
            val favorites = query.map { it.id }
            deferred.complete(Success(favorites))
        } catch (exception: FirebaseFirestoreException) {
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }

    suspend fun getFavorites(userId: String): DataResult<List<FavoriteModel>, String> {
        val deferred = CompletableDeferred<DataResult<List<FavoriteModel>, String>>()
        try {
            val query =
                firestore.collection(COLLECTION).document(userId).collection(COIN_COLLECTION).get()
                    .await().documents
            val favorites = query.map {
                FavoriteModel(
                    it.getString("coinId"),
                    it.getString("coinName"),
                    it.getString("coinPrice"),
                    it.getString("coinSymbol"),
                    it.getString("coinImage")
                )
            }
            deferred.complete(Success(favorites))
        } catch (exception: FirebaseFirestoreException) {
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }


    companion object {
        const val AUTH_ERROR = "ERROR_USER_NOT_FOUND"
        const val COLLECTION = "favorites"
        const val COIN_COLLECTION = "coins"
    }
}