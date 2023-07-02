package com.alicimsamil.cointracker.domain.usecase

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.database.model.CoinDBModel
import com.alicimsamil.cointracker.domain.repository.CoinRepository
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class AddRemoteCoinsToDbUseCase @Inject constructor(private val repository: CoinRepository) {

    suspend operator fun invoke(): DataResult<Unit, String> {
        val deferred = CompletableDeferred<DataResult<Unit, String>>()
        repository.getCoins()
            .onSuccess {
                it?.let {
                    repository.addCoins(it.map {
                        CoinDBModel(it.id, it.name, it.symbol)
                    }).onSuccess {
                        deferred.complete(Success(Unit))
                    }.onFailure {
                        deferred.complete(Error(it))
                    }
                }
            }.onFailure {
                deferred.complete(Error(it))
            }
        return deferred.await()
    }
}
