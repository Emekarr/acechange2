package com.example.acechange20.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_entity")
data class CurrencyEntity(
    @PrimaryKey
    var currency: String,
    var value: Float
)