package com.example.acechange20.screens.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.DialogFragment
import com.example.acechange20.R
import com.example.acechange20.databinding.BaseCurrencyDialogFragmentBinding
import com.example.acechange20.repository.BaseCurrency

class BaseCurrencyDialogFragment: DialogFragment() {
    private lateinit var binding: BaseCurrencyDialogFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.base_currency_dialog_fragment, container, false)

        val baseCurrencyList = arrayOf("EUR", "CAD", "HKD", "USD", "KRW", "JPY", "NZD")

        binding.selectBaseCurrency.adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, baseCurrencyList)

        binding.selectBaseCurrency.onItemClickListener =
            AdapterView.OnItemClickListener { parent, view, position, id ->
                BaseCurrency.baseCurrency.value = baseCurrencyList[position]
                this.dismiss()
            }

        return binding.root
    }
}