package com.example.bettingstrategies.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favorite_strategies_table")
data class StrategyData(
    @PrimaryKey(autoGenerate = false)
    var title: String,
    @ColumnInfo(name = "img_src_url")
    var imgSrcUrl: String,
    @ColumnInfo(name = "description")
    var description: String
)