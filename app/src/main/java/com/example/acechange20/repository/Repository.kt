package com.example.acechange20.repository

import android.content.Context
import com.example.acechange20.database.CurrencyDatabase
import com.example.acechange20.database.entities.CurrencyEntity
import com.example.acechange20.retrofit.ExchangeDto
import com.example.acechange20.retrofit.ExchangeRates
import com.example.acechange20.retrofit.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.reflect.full.memberProperties

class Repository(databaseCompanion: CurrencyDatabase.Companion, context: Context, private val retrofit: Retrofit){
    private val database = databaseCompanion.getInstance(context)

    //monitor cached results in database
    val cachedResults = database.currencyDao().getCache()

    //get rates from api
    fun getAllRates(){
        val job = Job()
        val ioScope = CoroutineScope(Dispatchers.IO + job)

        ioScope.launch {
            val rates = retrofit.retrofitApi.getAllRates("EUR").await()

            convertRatesToCurrencyEntity(rates)
            job.cancel()
        }
    }

    //convert rates to database entity
    private fun convertRatesToCurrencyEntity(rates: ExchangeDto) {
        val results = mutableListOf<CurrencyEntity>()
        for (i in ExchangeRates::class.memberProperties){
            i.get(rates.rates)?.let{
                results.add(CurrencyEntity(i.name, it as Float))
            }
        }
        
        cacheResults(results)
    }

    //save rates to database
    private fun cacheResults(results: MutableList<CurrencyEntity>) {
        val job = Job()
        val ioScope = CoroutineScope(Dispatchers.IO + job)

        ioScope.launch {
            database.currencyDao().cacheResults(*results.toTypedArray())
            job.cancel()
        }
    }

}