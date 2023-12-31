package com.alicimsamil.cointracker.core.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.alicimsamil.cointracker.core.database.model.TableConstants.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class CoinDBModel(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo(name = "coinId")
    val coinId: String,
    @ColumnInfo(name = "name")
    val name: String?,
    @ColumnInfo(name = "symbol")
    val symbol: String?
)


object TableConstants{
    const val TABLE_NAME = "coin_table"
}
