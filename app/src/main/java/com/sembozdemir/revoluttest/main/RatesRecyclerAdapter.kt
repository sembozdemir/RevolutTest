package com.sembozdemir.revoluttest.main

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.extensions.autoNotify
import com.sembozdemir.revoluttest.core.extensions.inflate

class RatesRecyclerAdapter(
    private var items: List<RateItem> = emptyList()
) : RecyclerView.Adapter<RateItemViewHolder>() {

    init {
        setHasStableIds(true)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RateItemViewHolder {
        return RateItemViewHolder(parent.inflate(R.layout.item_currency))
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RateItemViewHolder, position: Int) {
        holder.bind(items[position], position == 0)
    }

    override fun onBindViewHolder(
        holder: RateItemViewHolder,
        position: Int,
        payloads: MutableList<Any>
    ) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder, position)
        } else {
            holder.setRate(payloads.first() as Double)
        }
    }

    override fun getItemId(position: Int): Long {
        return items[position].code.hashCode().toLong()
    }

    fun updateItems(newItems: List<RateItem>) {
        val oldItems = items.toList()
        items = newItems
        autoNotify(oldItems, newItems,
            payload = { old, new ->
                if (old.rate != new.rate) {
                    new.rate
                } else {
                    null
                }
            },
            compare = { old, new ->
                old.code == new.code
            })
    }

}