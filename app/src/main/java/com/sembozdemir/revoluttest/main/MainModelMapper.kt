package com.sembozdemir.revoluttest.main

import com.sembozdemir.revoluttest.core.extensions.orZero
import com.sembozdemir.revoluttest.core.network.model.CurrencyResponse
import com.sembozdemir.revoluttest.core.network.model.Rates
import javax.inject.Inject
import kotlin.reflect.full.memberProperties

class MainModelMapper @Inject constructor() {

    fun toUIModel(response: CurrencyResponse): MainUIModel {
        val base = checkNotNull(response.base) { "base must not-null" }
        val rates = checkNotNull(response.rates) { "rates must not-null" }
        return MainUIModel(
            base,
            mapCurrencyItems(rates)
        )
    }

    private fun mapCurrencyItems(rates: Rates): List<RateItem> {
        val currencyItems = arrayListOf<RateItem>()
        for (prop in Rates::class.memberProperties) {
            currencyItems.add(
                RateItem(
                    prop.name.toUpperCase(),
                    "",
                    (prop.get(rates) as? Double).orZero()
                )
            )
        }
        return currencyItems
    }

}
