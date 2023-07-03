package com.alicimsamil.cointracker.feature.detail.domain.usecase

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.feature.detail.domain.repository.DetailRepository
import com.alicimsamil.cointracker.feature.detail.presentation.DetailModel
import kotlinx.coroutines.CompletableDeferred
import java.text.DecimalFormat
import javax.inject.Inject

class GetCoinDetailUseCase @Inject constructor(private val repository: DetailRepository) {
    suspend operator fun invoke(id: String): DataResult<DetailModel, String> {
        val deferred = CompletableDeferred<DataResult<DetailModel, String>>()
        repository.getCoinDetails(id)
            .onSuccess { data ->
                repository.getFavorites(repository.getUserId()).onSuccess { favorites->
                    deferred.complete(
                        Success(
                            DetailModel(
                                data?.name,
                                "(${data?.symbol?.uppercase()})",
                                favorites?.contains(data?.id),
                                data?.image?.large,
                                "$${
                                    String.format("%.2f", data?.marketData?.currentPrice?.usd)
                                }",
                                "(${
                                    String.format("%.2f", data?.marketData?.priceChangePercentage24h)
                                }%)",
                                data?.marketData?.priceChangePercentage24h,
                                data?.hashingAlgorithm,
                                data?.description?.en
                            )
                        )
                    )
                }
            }.onFailure {
                deferred.complete(Error(it))
            }
        return deferred.await()
    }
}