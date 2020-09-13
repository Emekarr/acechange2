package com.example.acechange20.screens.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.acechange20.R
import com.example.acechange20.databinding.FragmentAllRatesBinding
import com.example.acechange20.repository.BaseCurrency
import com.example.acechange20.screens.recyclerview.RecyclerView
import com.example.acechange20.screens.viewmodel.AllRatesViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

class AllRatesFragment : Fragment() {
    private val viewModel: AllRatesViewModel by lazy {
        getViewModel<AllRatesViewModel>()
    }
    private lateinit var binding: FragmentAllRatesBinding
    private val baseCurrencyDialogFragment = get<BaseCurrencyDialogFragment>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        getImageIfConnectedToInternet()

        viewModel.monitorCachedResults()

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_rates, container, false)

        binding.baseCurency = BaseCurrency
        binding.lifecycleOwner = this

        //set up swipe to refresh
        swipeToRefreshPage()

        //change base currency
        selectBaseCurrency()

        //check for when base currency is changed and make a new request
        observeBaseCurrency()

        //set up recyclerview
        setUpRecyclerView()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun getImageIfConnectedToInternet(){
        if (viewModel.checkInternetAvailability()) viewModel.getAllRates()
        else Snackbar.make(binding.snackBarLayout, "Please check your internet connection", Snackbar.LENGTH_SHORT).show()
    }

    private fun selectBaseCurrency() {
        binding.baseCurrency.setOnClickListener {
            baseCurrencyDialogFragment.show(requireActivity().supportFragmentManager, "")
        }
    }

    private fun observeBaseCurrency() {
        BaseCurrency.baseCurrency.observe(viewLifecycleOwner, Observer {
            if (viewModel.checkInternetAvailability()) viewModel.getAllRates()
            else Snackbar.make(
                binding.snackBarLayout,
                "Please check your internet connection",
                Snackbar.LENGTH_SHORT
            ).show()
        })
    }

    private fun swipeToRefreshPage() {
        binding.swipeToRefresh.setOnRefreshListener {
            if (viewModel.checkInternetAvailability()) viewModel.getAllRates()
            else Snackbar.make(
                binding.snackBarLayout,
                "Please check your internet connection",
                Snackbar.LENGTH_SHORT
            ).show()
            binding.swipeToRefresh.isRefreshing = false
        }
    }

    private fun setUpRecyclerView() {
        val adapter = get<RecyclerView>()
        binding.recyclerView.adapter = adapter

        viewModel.recyclerViewObject.observe(viewLifecycleOwner, Observer { currencies ->
                adapter.submitList(currencies)
        })
    }

}
