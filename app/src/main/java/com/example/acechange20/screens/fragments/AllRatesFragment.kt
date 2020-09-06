package com.example.acechange20.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.acechange20.R
import com.example.acechange20.screens.viewmodel.AllRatesViewModel
import org.koin.android.ext.android.get

class AllRatesFragment : Fragment() {
    private val viewModel: AllRatesViewModel by lazy {
        get<AllRatesViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewModel

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_rates, container, false)
    }

}