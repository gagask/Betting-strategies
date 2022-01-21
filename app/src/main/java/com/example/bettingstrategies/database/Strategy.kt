package com.example.bettingstrategies.database


import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
data class Strategy(
    var imgSrcUrl: String,
    var title: String,
    var description: String,
    var isFavorite: Boolean? = null
): Parcelable{
    constructor(strategyData: StrategyData, favorite: Boolean) :
            this(strategyData.imgSrcUrl, strategyData.title, strategyData.description, favorite)
}
