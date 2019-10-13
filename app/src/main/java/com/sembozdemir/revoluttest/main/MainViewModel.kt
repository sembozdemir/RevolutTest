package com.sembozdemir.revoluttest.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.sembozdemir.revoluttest.core.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val fetchCurrenciesAction: FetchCurrenciesAction
) : BaseViewModel() {

    val state: LiveData<MainState> get() = mutableState

    private val mutableState: MutableLiveData<MainState> = MutableLiveData()

    fun startFetchCurrencies() {
        mutableState.postValue(MainState.Loading)
        viewModelScope.launch {
            fetchCurrenciesAction.schedule("EUR", mutableState)
        }
    }

    override fun onCleared() {
        super.onCleared()
        fetchCurrenciesAction.cancel()
    }

}
