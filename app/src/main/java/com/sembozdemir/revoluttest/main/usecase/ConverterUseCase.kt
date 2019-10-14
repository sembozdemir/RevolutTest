package com.sembozdemir.revoluttest.main.usecase

import androidx.lifecycle.MutableLiveData
import com.sembozdemir.revoluttest.main.MainState
import com.sembozdemir.revoluttest.main.MainUIModel
import com.sembozdemir.revoluttest.main.RateItem
import javax.inject.Inject

class ConverterUseCase @Inject constructor() {

    fun execute(
        baseRate: Double,
        data: MainUIModel,
        state: MutableLiveData<MainState>
    ) {
        val convertedItems = arrayListOf<RateItem>().apply {
            add(RateItem(data.base, "", baseRate))
        }
        convertedItems.addAll(data.items.map {
            val convertedRate = it.rate * baseRate
            it.copy(rate = convertedRate)
        })

        state.postValue(MainState.Success(data.copy(items = convertedItems)))
    }
}