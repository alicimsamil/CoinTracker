package com.alicimsamil.cointracker.core.database.util

object DatabaseErrors {
    const val INVALID_TABLE_NAME_ERROR = "Table creation error: Invalid table name or table fields not properly defined."
    const val DATABASE_TRANSACTION_ERROR = "Database transaction error: Database thread stopped while transaction was executing."
    const val STALE_DATA_ERROR = "Query results are stale: Please re-run the query or update the database."
    const val INVALID_QUERY_ERROR = "Invalid query parameters: Please use correct data types or properly configure the parameters."
}