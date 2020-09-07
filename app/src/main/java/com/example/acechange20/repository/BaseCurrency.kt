package com.example.acechange20.repository

import androidx.lifecycle.MutableLiveData

object BaseCurrency {
    val baseCurrency = MutableLiveData<String>()

    init {
        baseCurrency.value = "EUR"
    }
}