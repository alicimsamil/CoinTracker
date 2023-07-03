package com.alicimsamil.cointracker.feature.detail.presentation

import androidx.lifecycle.viewModelScope
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.feature.detail.domain.usecase.AddFavoriteUseCase
import com.alicimsamil.cointracker.feature.detail.domain.usecase.GetChartDataUseCase
import com.alicimsamil.cointracker.feature.detail.domain.usecase.GetCoinDetailUseCase
import com.alicimsamil.cointracker.feature.detail.domain.usecase.RemoveFavoriteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val getCoinDetailUseCase: GetCoinDetailUseCase,
    private val chartDataUseCase: GetChartDataUseCase,
    private val addFavoriteUseCase: AddFavoriteUseCase,
    private val removeFavoriteUseCase: RemoveFavoriteUseCase
) :
    BaseViewModel() {

    var coinId: String? = null

    var state = MutableStateFlow(DetailUiState())
        private set

    private fun addFavorite(
        coinId: String,
        coinName: String?,
        coinPrice: String?,
        coinSymbol: String?,
        coinImage: String?
    ) {
        viewModelScope.launch {
            addFavoriteUseCase.invoke(coinId, coinName, coinPrice, coinSymbol, coinImage)
                .onSuccess {
                    state.value =
                        state.value.copy(detailModel = state.value.detailModel?.copy(isFavorite = true))
                }
                .onFailure {
                    state.value =
                        state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }

    private fun removeFavorite(
        coinId: String
    ) {
        viewModelScope.launch {
            removeFavoriteUseCase.invoke(coinId)
                .onSuccess {
                    state.value =
                        state.value.copy(detailModel = state.value.detailModel?.copy(isFavorite = false))
                }
                .onFailure {
                    state.value =
                        state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }

    private fun getCoinDetails(id: String) {
        coinId = id
        getChartData()
        CoroutineScope(Dispatchers.IO).launch {
            state.value = state.value.copy(isLoading = true)
            getCoinDetailUseCase.invoke(id)
                .onSuccess {
                    state.value = state.value.copy(isLoading = false, detailModel = it)
                }
                .onFailure {
                    state.value = state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }

    private fun getChartData() {
        state.value = state.value.copy(isLoading = true)
        viewModelScope.launch {
            coinId?.let {
                chartDataUseCase.invoke(it)
                    .onSuccess {
                        state.value = state.value.copy(isLoading = false, marketChartData = it)
                    }.onFailure {
                        state.value =
                            state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                    }
            }
        }
    }

    private fun refreshCoinPrice(second: Long?) {
        second?.let {
            coinId?.let { id ->
                viewModelScope.launch {
                    while (isActive) {
                        getCoinDetails(id)
                        delay(second * 1000)
                    }
                }
            }
        }
    }


    override fun onEvent(event: UiEvent) {
        when (event) {
            is DetailEvents.GetCoinDetails -> {
                getCoinDetails(event.id)
            }

            is DetailEvents.RefreshCoin -> {
                refreshCoinPrice(event.interval)
            }

            is DetailEvents.AddFavorite -> {
                coinId?.let {
                    addFavorite(
                        it,
                        state.value.detailModel?.name,
                        state.value.detailModel?.price,
                        state.value.detailModel?.symbol,
                        state.value.detailModel?.icon
                    )
                }
            }

            is DetailEvents.RemoveFavorite -> {
                coinId?.let {
                    removeFavorite(it)
                }
            }
        }
    }
}

sealed class DetailEvents : UiEvent {
    data class GetCoinDetails(val id: String) : DetailEvents()
    data class RefreshCoin(val interval: Long?) : DetailEvents()
    object AddFavorite : DetailEvents()
    object RemoveFavorite : DetailEvents()
}

data class DetailUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    var detailModel: DetailModel? = null,
    val marketChartData: List<Double>? = null
) : UiState