package com.sembozdemir.revoluttest.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.base.BaseActivity
import com.sembozdemir.revoluttest.core.network.model.CurrencyResponse
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel()= provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        observeState()

        viewModel.startFetchCurrencies()
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer { state ->
            when (state) {
                is MainState.Error -> showError(state.message)
                is MainState.Loading -> mainSwipeRefreshLayout.isRefreshing = true
                is MainState.Success -> showSuccess(state.data)
            }
        })
    }

    private fun showSuccess(data: CurrencyResponse) {
        mainSwipeRefreshLayout.isRefreshing = false
        Timber.d(data.rates.toString())
    }

    private fun showError(message: String) {
        mainSwipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
