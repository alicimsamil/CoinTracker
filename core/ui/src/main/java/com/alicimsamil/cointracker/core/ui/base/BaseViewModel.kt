package com.alicimsamil.cointracker.core.ui.base

import androidx.lifecycle.ViewModel

/**
 * This ViewModel collects the base functions done in other ViewModels.
 * Other view models must inherit this ViewModel.
 */
abstract class BaseViewModel : ViewModel() {

    /**
     * The onEvent function is written to manage the
     * communication between fragment and ViewModel from one place.
     */
    abstract fun onEvent(event: UiEvent)

}