package com.example.innovationmvvmtest.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currencies")
data class CurrencyItem(@PrimaryKey val symbol: String, val name: String, val rate: Double)