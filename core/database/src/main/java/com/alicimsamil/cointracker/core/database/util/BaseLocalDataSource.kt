package com.alicimsamil.cointracker.core.database.util

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import android.os.DeadObjectException
import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.ErrorMessages
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.database.BuildConfig

/**
 * This class makes success and fail situations in database operations easier,
 * cleaner and more readable.
 */
open class BaseLocalDataSource {
    suspend fun <T> performDatabaseOperation(
        operation: suspend () -> T
    ): DataResult<T, String> {
        return try {
            Success(operation.invoke())
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG)
                exception.printStackTrace()
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): DataResult<Nothing, String> {
        return when (exception) {
            is SQLiteException -> Error( DatabaseErrors.INVALID_TABLE_NAME_ERROR )

            is StaleDataException -> Error( DatabaseErrors.STALE_DATA_ERROR )

            is IllegalArgumentException -> Error( DatabaseErrors.INVALID_QUERY_ERROR )

            is DeadObjectException -> Error( DatabaseErrors.DATABASE_TRANSACTION_ERROR )

            else -> Error(exception.message ?: ErrorMessages.DEFAULT_ERROR)
        }
    }
}