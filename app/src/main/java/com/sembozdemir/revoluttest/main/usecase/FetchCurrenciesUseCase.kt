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
        get() = Dispatchers.IO + job

    fun cancel() {
        Timber.d("Cancelling fetching currencies...")
        job.cancel()
        job = Job()
    }

    fun schedule(base: String, scrollTop: Boolean, mutableState: MutableLiveData<MainState>): Job {
        return launch {
            mutableState.postValue(MainState(loading = true))
            var mutableScrollTop = scrollTop
            while (true) {
                Timber.d("Fetching currencies...")
                delay(1000)
                fetch(base, mutableScrollTop, mutableState)
                mutableScrollTop = false
            }
        }
    }

    private suspend fun fetch(base: String, scrollTop: Boolean, mutableState: MutableLiveData<MainState>) {
        val newState: MainState = try {
            val response = currencyApi.getLatest(base)
            if (!response.isSuccessful) {
                cancel()
                MainState(errorMessage = errorHandler.getEmptyResponseMessage())
            } else {
                response.body()?.let {
                    MainState(data = modelMapper.toUIModel(it), scrollTop = scrollTop)
                } ?: MainState(errorMessage = errorHandler.getEmptyResponseMessage()).also { cancel() }
            }
        } catch (e: Exception) {
            Timber.e(e)
            cancel()
            MainState(errorMessage = errorHandler.getPrettyMessage(e))
        }

        mutableState.postValue(newState)
    }
}