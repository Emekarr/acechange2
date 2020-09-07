package com.example.acechange20.screens.recyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.acechange20.R
import kotlinx.android.synthetic.main.currency_recyclerview_card.view.*

class RecyclerView : ListAdapter<RecyclerViewObject, com.example.acechange20.screens.recyclerview.RecyclerView.RecyclerViewHolder>(DiffUtil()) {
    class RecyclerViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(recyclerViewObject: RecyclerViewObject) {
            view.currencyName.text = recyclerViewObject.currency
            view.value.text = recyclerViewObject.value.toString()
        }
    }

    class DiffUtil : androidx.recyclerview.widget.DiffUtil.ItemCallback<RecyclerViewObject>() {
        override fun areItemsTheSame(
            oldItem: RecyclerViewObject,
            newItem: RecyclerViewObject
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: RecyclerViewObject,
            newItem: RecyclerViewObject
        ): Boolean {
            return oldItem.currency == newItem.currency
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.currency_recyclerview_card, parent, false)

        return RecyclerViewHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerViewHolder, position: Int) {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }
}