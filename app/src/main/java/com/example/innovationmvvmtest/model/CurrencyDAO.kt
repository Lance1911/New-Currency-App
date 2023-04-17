package com.example.innovationmvvmtest.model

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCurrencyItems(currencies: List<CurrencyItem>)

    @Query("DELETE FROM currencies")
    suspend fun deleteAllCurrencies()

    @Query("SELECT * FROM currencies ORDER BY name ASC")
    fun getAllCurrencies(): Flow<List<CurrencyItem>>
}