package com.sembozdemir.revoluttest.main

import androidx.lifecycle.MutableLiveData
import com.sembozdemir.revoluttest.core.network.CurrencyApi
import com.sembozdemir.revoluttest.core.util.ErrorHandler
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchCurrenciesAction @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val errorHandler: ErrorHandler
): CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun cancel() {
        Timber.d("Cancelling fetching currencies...")
        job.cancel()
    }

    fun schedule(base: String, mutableState: MutableLiveData<MainState>) = launch {
        while (true) {
            Timber.d("Fetching currencies...")
            delay(1000)
            fetch(base, mutableState)
        }
    }

    private suspend fun fetch(base: String, mutableState: MutableLiveData<MainState>) {

        val newState: MainState = try {
            val response = currencyApi.getLatest(base)
            if (!response.isSuccessful) {
                cancel()
                MainState.Error(errorHandler.getEmptyResponseMessage())
            } else {
                response.body()?.let {
                    MainState.Success(it)
                } ?: MainState.Error(errorHandler.getEmptyResponseMessage()).also { cancel() }
            }
        } catch (e: Exception) {
            cancel()
            MainState.Error(errorHandler.getPrettyMessage(e))
        }

        mutableState.postValue(newState)
    }
}