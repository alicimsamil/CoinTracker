package com.alicimsamil.cointracker.presentation

import androidx.lifecycle.viewModelScope
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.domain.usecase.AddRemoteCoinsToDbUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val useCase: AddRemoteCoinsToDbUseCase) :
    BaseViewModel() {

    var state = MutableStateFlow(MainUiState())
        private set

    init {
        addCoinsToDb()
    }

    private fun addCoinsToDb() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            useCase.invoke()
                .onSuccess {
                    state.value = state.value.copy(isLoading = false, splashState = false)

                }.onFailure {
                    state.value = state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }


    override fun onEvent(event: UiEvent) {
    }

}

data class MainUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    val splashState: Boolean = true
) : UiState