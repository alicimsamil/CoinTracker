package com.alicimsamil.cointracker.core.network

import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.network.util.BaseRemoteDataSource
import com.alicimsamil.cointracker.core.network.util.NetworkErrors
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before
import retrofit2.Response
import java.net.SocketTimeoutException

class BaseRemoteDataSourceTest {

    private lateinit var dataSource: BaseRemoteDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        dataSource = BaseRemoteDataSource()
    }

    @Test
    fun perform_network_call_should_return_success_with_correct_value() = runTest(testDispatcher) {
        val result = dataSource.performApiCall { Response.success(44) }
        assertTrue(result is Success)
        assertEquals(44, (result as Success).data)
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_400_codes() = runTest(testDispatcher) {
        val result = dataSource.performApiCall { Response.error<String>(400, "Client error".toResponseBody("text/plain".toMediaTypeOrNull())) }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(NetworkErrors.CLIENT_ERROR, it)
        }
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_500_codes() = runTest(testDispatcher) {
        val result = dataSource.performApiCall { Response.error<String>(500, "Server error".toResponseBody("text/plain".toMediaTypeOrNull())) }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(NetworkErrors.SERVER_ERROR, it)
        }
    }

    @Test
    fun perform_network_call_should_return_error_with_correct_message_for_SocketTimeoutException() = runTest(testDispatcher) {
        val mockCall: suspend () -> Response<String> = { throw SocketTimeoutException() }

        val result = dataSource.performApiCall { mockCall() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(NetworkErrors.SLOW_INTERNET_CONNECTION, it)
        }
    }
}