package com.alicimsamil.cointracker.core.ui.base

/**
 * The UiState Interface structure creates a base
 * for the states we use when communicating between
 * Fragment and ViewModels.
 */
interface UiState{
    var isLoading: Boolean
    var error: String
}