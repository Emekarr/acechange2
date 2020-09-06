package com.example.acechange20.repository

import android.content.Context
import android.util.Log
import com.example.acechange20.database.CurrencyDatabase
import com.example.acechange20.retrofit.Retrofit
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class Repository(databaseCompanion: CurrencyDatabase.Companion, context: Context, private val retrofit: Retrofit){
    private val database = databaseCompanion.getInstance(context)

    fun getAllRates(){
        val job = Job()
        val ioScope = CoroutineScope(Dispatchers.IO + job)

        ioScope.launch {
            val rates = retrofit.retrofitApi.getAllRates("EUR").await()
            Log.i("test", rates.rates.BGN.toString())

            job.cancel()
        }
    }

}