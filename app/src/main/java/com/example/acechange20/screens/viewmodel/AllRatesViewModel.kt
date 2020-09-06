package com.example.acechange20.screens.viewmodel

import androidx.lifecycle.ViewModel
import com.example.acechange20.repository.Repository

class AllRatesViewModel(private val repository: Repository) : ViewModel() {

    init {
        repository.getAllRates()
    }
}