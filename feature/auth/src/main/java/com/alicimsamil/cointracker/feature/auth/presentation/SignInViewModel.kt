package com.alicimsamil.cointracker.feature.auth.presentation

import androidx.lifecycle.viewModelScope
import com.alicimsamil.cointracker.core.common.extension.EMPTY
import com.alicimsamil.cointracker.core.ui.base.BaseViewModel
import com.alicimsamil.cointracker.core.ui.base.UiEvent
import com.alicimsamil.cointracker.core.ui.base.UiState
import com.alicimsamil.cointracker.feature.auth.domain.usecase.GetLoggedInInfoUseCase
import com.alicimsamil.cointracker.feature.auth.domain.usecase.SignInUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val signInUseCase: SignInUseCase,
    private val isLoggedInInfoUseCase: GetLoggedInInfoUseCase
) :
    BaseViewModel() {

    init {
        isLoggedIn()
    }

    var state = MutableStateFlow(SignInUiState())
        private set

    private fun signIn(email: String, password: String) {
        viewModelScope.launch {
            state.value = state.value.copy(isLoading = true)
            signInUseCase.invoke(email, password)
                .onSuccess {
                    state.value = state.value.copy(signInSuccess = true)
                }
                .onFailure {
                    state.value = state.value.copy(isLoading = false, error = it ?: String.EMPTY)
                }
        }
    }

    private fun isLoggedIn() {
        viewModelScope.launch {
            state.value = state.value.copy(signInSuccess = isLoggedInInfoUseCase.invoke())
        }
    }

    override fun onEvent(event: UiEvent) {
        when (event) {
            is SignInEvents.SignIn -> {
                signIn(event.email, event.password)
            }
        }
    }
}

sealed class SignInEvents : UiEvent {
    data class SignIn(val email: String, val password: String) : SignInEvents()
}

data class SignInUiState(
    override var isLoading: Boolean = false,
    override var error: String = String.EMPTY,
    var signInSuccess: Boolean = false
) : UiState