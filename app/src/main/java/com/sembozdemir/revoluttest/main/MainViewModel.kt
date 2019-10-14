package com.sembozdemir.revoluttest.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sembozdemir.revoluttest.core.base.BaseViewModel
import com.sembozdemir.revoluttest.main.usecase.ConverterUseCase
import com.sembozdemir.revoluttest.main.usecase.FetchCurrenciesUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchCurrenciesUseCase: FetchCurrenciesUseCase,
    private val converterUseCase: ConverterUseCase
) : BaseViewModel() {

    val state: LiveData<MainState> get() = convertedState

    private val rawState: MutableLiveData<MainState> = MutableLiveData()

    private val convertedState: MediatorLiveData<MainState> = MediatorLiveData()

    private var baseRate: Double = 1.0

    init {
        convertedState.addSource(rawState) { state ->
            if (state is MainState.Success) {
                converterUseCase.execute(
                    baseRate,
                    state.data,
                    convertedState
                )
            }
        }
    }

    fun startFetchCurrencies() {
        rawState.postValue(MainState.Loading)
        viewModelScope.launch {
            fetchCurrenciesUseCase.schedule("EUR", rawState)
        }
    }

    fun stopFetchCurrencies() {
        fetchCurrenciesUseCase.cancel()
    }

    override fun onCleared() {
        fetchCurrenciesUseCase.cancel()
    }

}
