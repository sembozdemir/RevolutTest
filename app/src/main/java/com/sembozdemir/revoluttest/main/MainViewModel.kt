package com.sembozdemir.revoluttest.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import com.sembozdemir.revoluttest.core.base.BaseViewModel
import com.sembozdemir.revoluttest.main.usecase.ConverterUseCase
import com.sembozdemir.revoluttest.main.usecase.FetchCurrenciesUseCase
import java.math.BigDecimal
import javax.inject.Inject

private const val DEFAULT_CURRENCY_CODE = "EUR"

class MainViewModel @Inject constructor(
    private val fetchCurrenciesUseCase: FetchCurrenciesUseCase,
    private val converterUseCase: ConverterUseCase
) : BaseViewModel() {

    val state: LiveData<MainState> get() = convertedState

    private val rawState: MutableLiveData<MainState> = MutableLiveData()

    private val convertedState: MediatorLiveData<MainState> = MediatorLiveData()

    private var baseRate: BigDecimal = BigDecimal.ONE

    init { initMediatorState() }

    private fun initMediatorState() {
        convertedState.addSource(rawState) { state ->
            if (state.data != null) {
                converterUseCase.execute(
                    baseRate,
                    state.data,
                    state.scrollTop,
                    convertedState
                )
            } else {
                convertedState.postValue(state)
            }
        }
    }

    fun handleEvent(event: MainEvent) {
        when (event) {
            is MainEvent.StartFetchCurrenciesEvent -> startFetchCurrencies()
            is MainEvent.CurrencySelectionEvent -> {
                stopFetchCurrencies()
                startFetchCurrencies(event.code, scrollTop = true)
            }
            is MainEvent.BaseRateChangedEvent -> baseRate = event.baseRate
        }
    }

    private fun startFetchCurrencies(code: String = DEFAULT_CURRENCY_CODE, scrollTop: Boolean = false) {
        rawState.postValue(MainState(loading = true))
        fetchCurrenciesUseCase.schedule(code, scrollTop, rawState)
    }

    private fun stopFetchCurrencies() {
        fetchCurrenciesUseCase.cancel()
    }

    override fun onCleared() {
        fetchCurrenciesUseCase.cancel()
    }

}
