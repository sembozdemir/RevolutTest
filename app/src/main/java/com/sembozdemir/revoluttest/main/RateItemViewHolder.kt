package com.sembozdemir.revoluttest.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*

class RateItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: RateItem, enabled: Boolean) {
        itemView.apply {
            textViewCode.text = item.code
            textViewName.text = item.name
            editTextRate.setText(item.rate.toString())
            editTextRate.isEnabled = enabled
        }
    }

    fun setRate(rate: Double) {
        itemView.editTextRate.setText(rate.toString())
    }
}