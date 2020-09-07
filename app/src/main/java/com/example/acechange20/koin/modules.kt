package com.example.acechange20.koin

import com.example.acechange20.database.CurrencyDatabase
import com.example.acechange20.repository.Repository
import com.example.acechange20.retrofit.Retrofit
import com.example.acechange20.screens.recyclerview.RecyclerView
import com.example.acechange20.screens.viewmodel.AllRatesViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewmodels = module {
    viewModel { AllRatesViewModel(get()) }
}
val modules = module {
    single { Repository(get(), androidContext(), get()) }
    single { RecyclerView() }
    single { CurrencyDatabase }
    single { Retrofit }
}