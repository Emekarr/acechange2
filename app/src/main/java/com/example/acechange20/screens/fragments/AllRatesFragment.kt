package com.example.acechange20.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.example.acechange20.R
import com.example.acechange20.databinding.FragmentAllRatesBinding
import com.example.acechange20.screens.recyclerview.RecyclerView
import com.example.acechange20.screens.viewmodel.AllRatesViewModel
import com.google.android.material.snackbar.Snackbar
import org.koin.android.ext.android.get

class AllRatesFragment : Fragment() {
    private val viewModel: AllRatesViewModel by lazy {
        get<AllRatesViewModel>()
    }
    private lateinit var binding: FragmentAllRatesBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_all_rates, container, false)

        //call get all rates
        if (viewModel.checkInternetAvailability()) viewModel.getAllRates()
        else Snackbar.make(binding.snackBarLayout, "Please check your internet connection and try again", Snackbar.LENGTH_SHORT).show()

        //set up the recycler view
        setUpRecyclerView()


        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpRecyclerView() {
        val adapter = get<RecyclerView>()
        binding.recyclerView.adapter = adapter

        viewModel.recyclerViewObject.observe(viewLifecycleOwner, Observer {currencies ->
            currencies?.let{
                adapter.submitList(it)
            }
        })
    }

}