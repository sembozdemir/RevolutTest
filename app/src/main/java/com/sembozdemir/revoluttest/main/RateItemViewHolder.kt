package com.sembozdemir.revoluttest.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sembozdemir.revoluttest.core.extensions.toFormattedString
import com.sembozdemir.revoluttest.core.util.CurrencyResources
import kotlinx.android.synthetic.main.item_currency.view.*
import java.math.BigDecimal

class RateItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: RateItem) {
        val currencyResources = CurrencyResources(itemView.context)
        itemView.apply {
            textViewCode.text = item.code
            textViewName.text = context.getString(currencyResources.getNameResId(item.code))
            imageViewFlag.setImageResource(currencyResources.getFlagDrawableResId(item.code))
            editTextRate.setText(item.rate.toFormattedString())
            editTextRate.isEnabled = adapterPosition == 0
        }
    }

    fun setRate(rate: BigDecimal) {
        itemView.apply {
            if (adapterPosition == 0) {
                editTextRate.isEnabled = true
                editTextRate.setSelection(editTextRate.text.length)
            } else {
                editTextRate.isEnabled = false
                editTextRate.setText(if (rate != BigDecimal.ZERO) rate.toFormattedString() else "")
            }
        }
    }
}