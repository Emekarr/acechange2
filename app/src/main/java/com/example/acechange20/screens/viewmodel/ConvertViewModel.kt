package com.example.acechange20.screens.viewmodel

import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.acechange20.database.entities.CurrencyEntity
import com.example.acechange20.repository.Repository

class ConvertViewModel(private val repository: Repository) : ViewModel() {
    private var currencyList = listOf<CurrencyEntity>()

    val listOfCurrenciesToConvertFrom = listOf(
        "EUR",
        "CAD",
        "HKD",
        "USD",
        "ISK",
        "PHP",
        "DKK",
        "HUF",
        "KRW",
        "JPY",
        "NZD",
        "RUB",
        "BGN",
        "MXN",
        "THB",
        "IDR",
        "RON",
        "BRL",
        "CNY",
        "NOK",
        "CHF",
        "SEK"
    )

    private val cachedResultsObserver = Observer<List<CurrencyEntity>>{
        currencyList = it
    }

    fun getCurrencyValues(){
        repository.cachedResults.observeForever(cachedResultsObserver)
    }


    fun convertCurrencies(currency1: String?, currency2: String?): String{
        var value1 = 0f
        var value2 = 0f

        if (currency1 == currency2) return "1"
        else{
            for (i in currencyList){
                when (i.currency) {
                    currency1 -> value1 = i.value
                    currency2 -> value2 = i.value
                }
            }
        }
        return (value1 / value2).toString()
    }

    override fun onCleared() {
        super.onCleared()

        repository.cachedResults.removeObserver(cachedResultsObserver)
    }
}