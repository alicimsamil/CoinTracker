package com.alicimsamil.cointracker.core.common.result

/**
 * This class has been written for convenience
 * when moving data between the data layer
 * and the feature layer and making changes on it.
 */
sealed class DataResult<out V, out E>() {

    inline infix fun onSuccess(action: (V?) -> Unit): DataResult<V?, E> {
        if (this is Success) {
            action(data)
        }
        return this
    }

    inline infix fun onFailure(action: (E?) -> Unit): DataResult<V, E> {

        if (this is Error) {
            action(error)
        }
        return this
    }

}

data class Success<out V>(val data: V?) : DataResult<V, Nothing>()

data class Error<out E> (val error: E?) : DataResult<Nothing, E>()