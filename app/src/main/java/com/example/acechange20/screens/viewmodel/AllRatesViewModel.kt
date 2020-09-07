package com.example.acechange20.screens.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.acechange20.database.entities.CurrencyEntity
import com.example.acechange20.database.entities.toRecyclerViewObject
import com.example.acechange20.repository.Repository
import com.example.acechange20.screens.recyclerview.RecyclerViewObject

class AllRatesViewModel(private val repository: Repository) : ViewModel() {
    val recyclerViewObject = MutableLiveData<List<RecyclerViewObject>>()

    init {
        monitorCachedResults()
    }

    //get currency
    fun getAllRates(){
        repository.getAllRates()
    }


    //monitor the cached results
    private fun monitorCachedResults() {
        repository.cachedResults.observeForever{results ->
            recyclerViewObject.value = results.toRecyclerViewObject()
        }
    }

    //check for internet availability
    fun checkInternetAvailability(): Boolean {
        return try {
            val runtime = Runtime.getRuntime()
            val ipProcess = runtime.exec("/system/bin/ping -c 1 " + "www.google.com")
            val exitValue = ipProcess.waitFor()
            exitValue == 0
        } catch (e: Exception) {
            false
        }
    }

}