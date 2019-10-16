package com.sembozdemir.revoluttest.main

import android.os.Bundle
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : BaseActivity<MainViewModel>() {

    private val stateObserver: Observer<MainState> = Observer { state ->
        mainProgressBar.isVisible = state.loading
        state.errorMessage?.let { showError(it) }
        state.data?.let { showSuccess(it) }
        if (state.scrollTop) {
            mainRecyclerView.scrollToPosition(0)
        }
    }

    private val currenciesRecyclerAdapter = RatesRecyclerAdapter(
        onItemClick = { onItemClick(it) },
        onBaseRateChanged = { onBaseRateChanged(it) }
    )

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel() = provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()

        observeState()

        viewModel.startFetchCurrencies()
    }

    private fun setupRecyclerView() {
        mainRecyclerView.layoutManager = LinearLayoutManager(this)
        mainRecyclerView.adapter = currenciesRecyclerAdapter
        mainRecyclerView.setHasFixedSize(true)
    }

    private fun observeState() {
        viewModel.state.observe(this, Observer { state ->
            mainProgressBar.isVisible = state.loading
            state.errorMessage?.let { showError(it) }
            state.data?.let { showSuccess(it) }
            if (state.scrollTop) {
                mainRecyclerView.scrollToPosition(0)
            }
        })
    }

    private fun showSuccess(data: MainUIModel) {
        currenciesRecyclerAdapter.updateItems(data.items)
    }

    private fun showError(message: String) {
        Snackbar.make(mainCoordinatorLayout, message, Snackbar.LENGTH_INDEFINITE)
            .apply { setAction(R.string.retry) { viewModel.startFetchCurrencies() } }
            .show()
    }

    private fun onItemClick(rateItem: RateItem) {
        viewModel.stopFetchCurrencies()
        viewModel.startFetchCurrencies(code = rateItem.code, scrollTop = true)
    }

    private fun onBaseRateChanged(baseRate: BigDecimal) {
        viewModel.setBaseRate(baseRate)
    }
}
