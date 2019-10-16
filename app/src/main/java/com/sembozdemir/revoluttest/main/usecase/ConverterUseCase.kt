package com.sembozdemir.revoluttest.main.usecase

import androidx.lifecycle.MutableLiveData
import com.sembozdemir.revoluttest.main.MainState
import com.sembozdemir.revoluttest.main.MainUIModel
import com.sembozdemir.revoluttest.main.RateItem
import java.math.BigDecimal
import javax.inject.Inject

class ConverterUseCase @Inject constructor() {

    fun execute(
        baseRate: BigDecimal,
        data: MainUIModel,
        scrollTop: Boolean,
        state: MutableLiveData<MainState>
    ) {
        val convertedItems = arrayListOf<RateItem>().apply {
            add(RateItem(data.base, baseRate))
        }
        convertedItems.addAll(data.items.map {
            val convertedRate = it.rate * baseRate
            it.copy(rate = convertedRate)
        })

        state.postValue(
            MainState(
                data = data.copy(items = convertedItems),
                scrollTop = scrollTop
            )
        )
    }
}