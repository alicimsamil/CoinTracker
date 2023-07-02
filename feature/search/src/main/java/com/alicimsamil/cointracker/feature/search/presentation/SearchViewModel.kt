package com.alicimsamil.cointracker.feature.search.presentation

import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import androidx.paging.map
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.feature.search.domain.usecase.SearchCoinsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(private val searchUseCase: SearchCoinsUseCase) :
    BaseViewModel() {

    var state = MutableStateFlow(SearchUiState())
        private set

    private fun searchCoins(keyword: String?) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            searchUseCase.invoke(keyword)
                .onSuccess {
                    it?.let {
                        state.value = state.value.copy(
                            pagingData = Pager(
                                PagingConfig(
                                    pageSize = 10,
                                    enablePlaceholders = true
                                ),
                                pagingSourceFactory = { it }
                            ).flow.map { it.map { data-> SearchModel(data.coinId, data.name, data.symbol?.uppercase()) } }.cachedIn(viewModelScope)
                                .catch { exception ->
                                    state.value = state.value.copy(
                                        error = exception.message.orEmpty(),
                                        isLoading = false
                                    )
                                }
                            ,
                            isLoading = false
                        )
                    }
                }
                .onFailure {
                    state.value =
                        state.value.copy(
                            isLoading = false,
                            error = it.orEmpty()
                        )
                }
        }
    }

    private fun clearData(){
        state.value = state.value.copy(pagingData = flowOf(PagingData.empty<SearchModel>()) )
    }

    override fun onEvent(event: UiEvent) {
        when(event){
            is SearchEvents.SearchCoins -> {
                searchCoins(keyword = event.keyword)
            }
            is SearchEvents.ClearData -> {
                clearData()
            }
        }
    }
}

sealed class SearchEvents : UiEvent {
    data class SearchCoins(val keyword: String?) : SearchEvents()
    object ClearData : SearchEvents()
}

data class SearchUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    val pagingData: Flow<PagingData<SearchModel>>? = null
) : UiState