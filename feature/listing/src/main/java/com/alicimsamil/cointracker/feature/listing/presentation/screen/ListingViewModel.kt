package com.alicimsamil.cointracker.feature.listing.presentation.screen

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.feature.listing.domain.usecase.GetCoinsFromRemoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class ListingViewModel @Inject constructor(private val getCoinsFromRemoteUseCase: GetCoinsFromRemoteUseCase): BaseViewModel() {

    var state = MutableStateFlow(ListingUiState())
        private set

    private fun getPagingData() {
        state.value = state.value.copy(
            pagingData = getCoinsFromRemoteUseCase.invoke().cachedIn(viewModelScope)
        )
    }


    override fun onEvent(event: UiEvent) {
        when (event) {
            is ListingEvents.GetPagingData -> {
                getPagingData()
            }
        }
    }

}

sealed class ListingEvents : UiEvent {
    object GetPagingData : ListingEvents()
}

data class ListingUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    val pagingData: Flow<PagingData<ListingModel>>? = null
) : UiState
