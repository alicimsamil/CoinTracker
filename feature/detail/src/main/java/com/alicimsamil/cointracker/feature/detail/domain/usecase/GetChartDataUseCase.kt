package com.alicimsamil.cointracker.feature.detail.domain.usecase

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import kotlinx.coroutines.CompletableDeferred
import javax.inject.Inject

class GetChartDataUseCase @Inject constructor(private val repository: DetailRepository) {
    suspend operator fun invoke(id: String): DataResult<List<Double>, String> {
        val deferred = CompletableDeferred<DataResult<List<Double>, String>>()
        val arrayList = ArrayList<Double>()
        repository.getChartData(id)
            .onSuccess {
                it?.let {
                    it.prices?.forEachIndexed { index, data ->
                        data?.forEachIndexed { indexIn, double ->
                            if (indexIn == 1) {
                                double?.let {
                                    arrayList.add(double)
                                }
                            }
                        }
                        if (index == it.prices?.lastIndex) {
                            deferred.complete(Success(arrayList))
                        }
                    }
                }
            }.onFailure {
                deferred.complete(Error(it))
            }
        return deferred.await()
    }
}