package com.alicimsamil.cointracker.core.firebase.source

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.firebase.model.AuthModel
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

    fun getCurrentUserId() : String?{
        return auth.currentUser?.uid
    }

    suspend fun addFavorite(userId: String, coinId: String): DataResult<Boolean, String>{
        val deferred = CompletableDeferred<DataResult<Boolean, String>>()
        try {
            firestore.collection(COLLECTION).document(userId).update(FIELD, FieldValue.arrayUnion(coinId)).await()
            deferred.complete(Success(true))
        } catch (exception: FirebaseFirestoreException){
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }

    suspend fun removeFavorite(userId: String, coinId: String): DataResult<Boolean, String>{
        val deferred = CompletableDeferred<DataResult<Boolean, String>>()
        try {
            firestore.collection(COLLECTION).document(userId).update(FIELD, FieldValue.arrayRemove(coinId)).await()
            deferred.complete(Success(true))
        } catch (exception: FirebaseFirestoreException){
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }

    suspend fun getFavorites(userId: String): DataResult<List<String>, String> {
        val deferred = CompletableDeferred<DataResult<List<String>, String>>()
        try {
            val favorites = firestore.collection(COLLECTION).document(userId).get().await()
                .get(FIELD) as List<String>
            deferred.complete(Success(favorites))
        } catch (exception: FirebaseFirestoreException){
            deferred.complete(Error(exception.message))
        }
        return deferred.await()
    }



    companion object {
        const val AUTH_ERROR = "ERROR_USER_NOT_FOUND"
        const val COLLECTION = "favorites"
        const val FIELD = "id"
    }
}