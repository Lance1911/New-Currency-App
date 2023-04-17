package com.example.innovationmvvmtest.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.innovationmvvmtest.api.CurrencyAPI
import com.example.innovationmvvmtest.model.CurrencyRepository
import com.example.innovationmvvmtest.model.CurrencyResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyViewModel @Inject constructor(repository: CurrencyRepository) : ViewModel() {
    val currencies = repository.getCurrencies().asLiveData()
}