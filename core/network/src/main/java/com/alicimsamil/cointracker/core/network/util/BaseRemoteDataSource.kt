package com.alicimsamil.cointracker.core.network.util

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.ErrorMessages
import com.alicimsamil.cointracker.core.network.BuildConfig
import retrofit2.HttpException
import retrofit2.Response
import java.net.SocketTimeoutException
import java.net.UnknownHostException

/**
 * This class represents a base remote data source that manages network calls to remote data sources
 * and handles error conditions.
 */
open class BaseRemoteDataSource {
    suspend fun <T> performApiCall(
        call: suspend () -> Response<T>
    ): DataResult<T, String> {
        return try {
            val response = call.invoke()
            if(response.isSuccessful) {
                Success(response.body())
            } else {
                throw HttpException(response)
            }
        } catch (exception: Exception) {
            if (BuildConfig.DEBUG)
                exception.printStackTrace()
            handleError(exception)
        }
    }

    private fun handleError(exception: Exception): DataResult<Nothing, String> {
        return when(exception) {
            is HttpException -> {
                when (exception.code()) {
                    in 400..451 -> Error( NetworkErrors.CLIENT_ERROR )

                    in 500..599 -> Error( NetworkErrors.SERVER_ERROR )

                    else -> Error( NetworkErrors.UNEXPECTED_ERROR )
                }
            }

            is UnknownHostException -> Error( NetworkErrors.NO_INTERNET_CONNECTION )

            is SocketTimeoutException -> Error( NetworkErrors.SLOW_INTERNET_CONNECTION )

            else -> Error(exception.message ?: ErrorMessages.DEFAULT_ERROR )
        }
    }
}