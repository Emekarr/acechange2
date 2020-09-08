package com.example.acechange20.koin

import com.example.acechange20.database.CurrencyDatabase
import com.example.acechange20.repository.Repository
import com.example.acechange20.retrofit.Retrofit
import com.example.acechange20.screens.fragments.BaseCurrencyDialogFragment
import com.example.acechange20.screens.fragments.ConvertValues
import com.example.acechange20.screens.recyclerview.RecyclerView
import com.example.acechange20.screens.viewmodel.AllRatesViewModel
import com.example.acechange20.screens.viewmodel.ConvertViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModels = module {
    viewModel { AllRatesViewModel(get()) }
    viewModel { ConvertViewModel(get()) }
}

val modules = module {
    single { Repository(get(), androidContext(), get()) }
    single { RecyclerView() }
    single { BaseCurrencyDialogFragment() }
    single { CurrencyDatabase }
    single { Retrofit }
}