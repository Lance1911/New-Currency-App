package com.example.innovationmvvmtest.api

import com.example.innovationmvvmtest.model.CurrencyResponse
import retrofit2.http.GET

interface CurrencyAPI {
    companion object {
        const val BASE_URL = "https://openexchangerates.org/api/"
    }

    @GET("currencies.json")
    suspend fun getCurrencyNames(): Map<String, String>

    @GET("latest.json?app_id=703af7dc5acd47c3826e155bfed34c9b")
    suspend fun getCurrencyRates(): CurrencyResponse
}