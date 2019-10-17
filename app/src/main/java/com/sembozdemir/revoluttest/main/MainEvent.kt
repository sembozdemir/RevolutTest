package com.sembozdemir.revoluttest.main

import java.math.BigDecimal

sealed class MainEvent {
    object StartFetchCurrenciesEvent : MainEvent()
    data class CurrencySelectionEvent(val code: String) : MainEvent()
    data class BaseRateChangedEvent(val baseRate: BigDecimal) : MainEvent()
}