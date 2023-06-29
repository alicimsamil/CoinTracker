package com.alicimsamil.cointracker.core.database

import android.database.StaleDataException
import android.database.sqlite.SQLiteException
import com.alicimsamil.cointracker.core.common.result.Success
import com.alicimsamil.cointracker.core.common.result.Error
import com.alicimsamil.cointracker.core.common.result.ErrorMessages
import com.alicimsamil.cointracker.core.database.util.BaseLocalDataSource
import com.alicimsamil.cointracker.core.database.util.DatabaseErrors
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import java.io.IOException

class BaseLocalDataSourceTest {

    private lateinit var dataSource: BaseLocalDataSource

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        dataSource = BaseLocalDataSource()
    }

    @Test
    fun perform_database_operation_should_return_success_with_correct_value() = runTest(testDispatcher) {
        val result = dataSource.performDatabaseOperation { 42 }
        assertTrue(result is Success)
        assertEquals(42, (result as Success).data)
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_SQLiteException() = runTest(testDispatcher) {
        val result = dataSource.performDatabaseOperation { throw SQLiteException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(DatabaseErrors.INVALID_TABLE_NAME_ERROR, it)
        }
    }

    @Test
    fun perform_database_operation_should_return_error_with_correct_message_for_StaleDataException() = runTest(testDispatcher) {
        val result = dataSource.performDatabaseOperation { throw StaleDataException() }
        assertTrue(result is Error)
        result.onFailure {
            assertEquals(DatabaseErrors.STALE_DATA_ERROR, it)
        }
    }

    @Test
    fun perform_database_operation_should_return_correct_message_for_null_error_message() = runTest(testDispatcher) {
        val result = dataSource.performDatabaseOperation { throw IOException() }
        result.onFailure {
            assertEquals(ErrorMessages.DEFAULT_ERROR, it)
        }
    }
}