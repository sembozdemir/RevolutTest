package com.sembozdemir.revoluttest.main.usecase

import androidx.lifecycle.MutableLiveData
import com.sembozdemir.revoluttest.core.network.CurrencyApi
import com.sembozdemir.revoluttest.core.util.ErrorHandler
import com.sembozdemir.revoluttest.main.MainModelMapper
import com.sembozdemir.revoluttest.main.MainState
import kotlinx.coroutines.*
import timber.log.Timber
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class FetchCurrenciesUseCase @Inject constructor(
    private val currencyApi: CurrencyApi,
    private val errorHandler: ErrorHandler,
    private val modelMapper: MainModelMapper
): CoroutineScope {

    private var job: Job = Job()
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Default + job

    fun cancel() {
        Timber.d("Cancelling fetching currencies...")
        job.cancel()
    }

    fun schedule(base: String, mutableState: MutableLiveData<MainState>) = launch {
        mutableState.postValue(MainState.Loading)
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
                    MainState.Success(modelMapper.toUIModel(it))
                } ?: MainState.Error(errorHandler.getEmptyResponseMessage()).also { cancel() }
            }
        } catch (e: Exception) {
            Timber.e(e)
            cancel()
            MainState.Error(errorHandler.getPrettyMessage(e))
        }

        mutableState.postValue(newState)
    }
}