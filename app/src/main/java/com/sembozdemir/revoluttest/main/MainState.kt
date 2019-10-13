package com.sembozdemir.revoluttest.main

import com.sembozdemir.revoluttest.core.network.model.CurrencyResponse

sealed class MainState {
    class Success(val data: CurrencyResponse): MainState()
    class Error(val message: String): MainState()
    object Loading : MainState()
}