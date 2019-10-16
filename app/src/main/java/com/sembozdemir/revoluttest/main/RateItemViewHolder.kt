package com.sembozdemir.revoluttest.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.sembozdemir.revoluttest.core.util.CurrencyResources
import kotlinx.android.synthetic.main.item_currency.view.*

class RateItemViewHolder(
    itemView: View,
    private val onItemClick: (item: RateItem) -> Unit,
    private val onBaseRateChanged: (baseRate: Double) -> Unit
) : RecyclerView.ViewHolder(itemView) {

    fun bind(item: RateItem, enabled: Boolean) {
        val currencyResources = CurrencyResources(itemView.context)
        itemView.apply {
            setOnClickListener { onItemClick(item) }
            textViewCode.text = item.code
            textViewName.text = context.getString(currencyResources.getNameResId(item.code))
            imageViewFlag.setImageResource(currencyResources.getFlagDrawableResId(item.code))
            editTextRate.setText(item.rate.toString())
            editTextRate.isEnabled = enabled
            // FIXME: fix changing rate issue
//            editTextRate.doAfterTextChanged {
//                if (enabled) {
//                    val baseRate = it.toString().toDoubleOrNull().orZero()
//                    onBaseRateChanged(baseRate)
//                }
//            }
        }
    }

    fun setRate(rate: Double) {
        itemView.editTextRate.setText(rate.toString())
    }
}