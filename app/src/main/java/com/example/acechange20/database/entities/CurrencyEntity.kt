package com.example.acechange20.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.acechange20.screens.recyclerview.RecyclerViewObject

@Entity(tableName = "currency_entity")
data class CurrencyEntity(
    @PrimaryKey
    var currency: String,
    var value: Float
)

fun List<CurrencyEntity>.toRecyclerViewObject(): List<RecyclerViewObject>{
    return this.map {
        RecyclerViewObject(
            currency = it.currency,
            value = it.value
        )
    }
}