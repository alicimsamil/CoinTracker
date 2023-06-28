package com.alicimsamil.cointracker.core.common

import com.alicimsamil.cointracker.core.common.result.DataResult
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.common.result.Error
import org.junit.Assert.assertEquals
import org.junit.Test
import java.lang.Exception

class DataResultTest {

    @Test
    fun result_is_correct() {
        val expectedResult = "True Result"
        val actualResult: DataResult<String, Exception> = Success(expectedResult)

        actualResult
            .onSuccess {
                assertEquals(expectedResult, it)
            }
            .onFailure {
                assertEquals(expectedResult, it?.message)
            }

    }

    @Test
    fun result_is_failure() {
        val expectedResult = "Fail"
        val actualResult: DataResult<String, String> = Error(expectedResult)

        actualResult
            .onSuccess {
                assertEquals(expectedResult, it)
            }
            .onFailure {
                assertEquals(expectedResult, it)
            }
    }

    @Test
    fun result_is_failure_as_exception() {
        val expectedResult = ArrayIndexOutOfBoundsException()
        val actualResult: DataResult<String, Exception> = Error(expectedResult)

        actualResult
            .onSuccess {
                assertEquals(expectedResult, it)
            }
            .onFailure {
                assertEquals(expectedResult, it)
            }
    }

}