package com.example.innovationmvvmtest.model

data class CurrencyResponse(
    val base: String,
    val disclaimer: String,
    val license: String,
    val rates: Map<String, Double>,
    val timestamp: Int
)