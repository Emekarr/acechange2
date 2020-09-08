package com.example.acechange20.screens.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.MutableLiveData
import com.example.acechange20.R
import com.example.acechange20.databinding.FragmentConvertBinding
import com.example.acechange20.screens.viewmodel.ConvertViewModel
import org.koin.android.ext.android.get
import org.koin.androidx.viewmodel.ext.android.getViewModel

class ConvertFragment : Fragment() {
    private lateinit var binding: FragmentConvertBinding
    private val viewModel: ConvertViewModel by lazy {
        getViewModel<ConvertViewModel>()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_convert, container, false)

        binding.convertValue = ConvertValues
        binding.lifecycleOwner = this

        //get rates to convert from
        viewModel.getCurrencyValues()

        setUpConvertFromCurrencySpinner()
        setUpConvertToCurrencySpinner()
        convertButton()

        // Inflate the layout for this fragment
        return binding.root
    }

    private fun setUpConvertFromCurrencySpinner() {
        val currencyList = viewModel.listOfCurrenciesToConvertFrom
        binding.convertFromSpinner.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, currencyList)
        binding.convertFromSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.convertFrom.text = "convert from " + currencyList[position]
                    ConvertValues.convertFromLiveData.value = currencyList[position]
                }
            }
    }

    private fun setUpConvertToCurrencySpinner() {
        val currencyList = viewModel.listOfCurrenciesToConvertFrom
        binding.convertToSpinner.adapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, currencyList)
        binding.convertToSpinner.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {}

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    binding.convertTo.text = "convert to " + currencyList[position]
                    ConvertValues.convertToLiveData.value = currencyList[position]
                }
            }
    }

    private fun convertButton() {
        binding.convertButton.setOnClickListener {
            ConvertValues.convertedValue.value = viewModel.convertCurrencies(
                ConvertValues.convertFromLiveData.value,
                ConvertValues.convertToLiveData.value
            )
        }
    }
}

object ConvertValues {
    val convertFromLiveData = MutableLiveData<String>()
    val convertToLiveData = MutableLiveData<String>()
    val convertedValue = MutableLiveData<String>()
}