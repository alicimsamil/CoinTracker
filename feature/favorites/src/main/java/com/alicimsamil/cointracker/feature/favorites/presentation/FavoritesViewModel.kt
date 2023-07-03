package com.alicimsamil.cointracker.feature.favorites.presentation

import androidx.lifecycle.viewModelScope
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.firebase.model.FavoriteModel
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.feature.favorites.domain.usecase.GetFavoritesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val favoritesUseCase: GetFavoritesUseCase
) : BaseViewModel() {

    var state = MutableStateFlow(FavoritesUiState())
        private set

    private fun getFavorites() {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = false)
            favoritesUseCase.invoke()
                .onSuccess {
                    state.value = state.value.copy(isLoading = false, favorites = it)
                }.onFailure {
                    state.value = state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }


    override fun onEvent(event: UiEvent) {
        when(event){
            is FavoritesEvents.GetFavorites -> {
                getFavorites()
            }
        }
    }
}

sealed class FavoritesEvents : UiEvent {
    object GetFavorites : FavoritesEvents()
}

data class FavoritesUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    var favorites: List<FavoriteModel>? = null
) : UiState