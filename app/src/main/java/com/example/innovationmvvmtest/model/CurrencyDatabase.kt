package com.example.innovationmvvmtest.model

import androidx.room.Database
import androidx.room.RoomDatabase

/**
 * CurrencyItem table
 *
 * When making changes to the database schema, increment version number by 1
 */
@Database(entities = [CurrencyItem::class], version = 1)
abstract class CurrencyDatabase: RoomDatabase() {
    abstract fun currencyDao(): CurrencyDAO
}