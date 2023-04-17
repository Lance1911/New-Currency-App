package com.example.innovationmvvmtest.model

import androidx.room.withTransaction
import com.example.innovationmvvmtest.api.CurrencyAPI
import com.example.innovationmvvmtest.resource.networkBoundResource
import kotlinx.coroutines.delay
import javax.inject.Inject

class CurrencyRepository @Inject constructor(private val api: CurrencyAPI, private val db: CurrencyDatabase) {
    private val currencyDao = db.currencyDao()

    fun getCurrencies() = networkBoundResource(
        query = {
            currencyDao.getAllCurrencies()
        },
        fetch = {
            delay(2000)
            val response = api.getCurrencyRates().rates.toList()
            val names = api.getCurrencyNames().toList()
            val currencyList = ArrayList<CurrencyItem>()
            for (i in 0 until response.size) {
                currencyList.add(CurrencyItem(names[i].first, names[i].second, response[i].second))
            }
            currencyList
        },
        saveFetchedResult = {
            db.withTransaction {
                currencyDao.deleteAllCurrencies()
                currencyDao.insertCurrencyItems(it.toList())
            }
        }
    )
}