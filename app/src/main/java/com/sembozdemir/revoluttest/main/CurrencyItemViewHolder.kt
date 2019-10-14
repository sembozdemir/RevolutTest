package com.sembozdemir.revoluttest.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_currency.view.*

class CurrencyItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: CurrencyItem) {
        itemView.apply {
            textViewCode.text = item.code
            textViewName.text = item.name
            textViewRate.text = item.rate.toString()
        }
    }

    fun setRate(rate: Double) {
        itemView.textViewRate.text = rate.toString()
    }
}