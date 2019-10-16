package com.sembozdemir.revoluttest.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private val stateObserver: Observer<MainState> = Observer { state ->
        mainSwipeRefreshLayout.isRefreshing = state.loading
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

    override fun createViewModel()= provideViewModel<MainViewModel>()

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
        viewModel.state.observe(this, stateObserver)
    }

    private fun showSuccess(data: MainUIModel) {
        currenciesRecyclerAdapter.updateItems(data.items)
    }

    private fun showError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    private fun onItemClick(rateItem: RateItem) {
        viewModel.stopFetchCurrencies()
        viewModel.startFetchCurrencies(code = rateItem.code, scrollTop = true)
    }

    private fun onBaseRateChanged(baseRate: Double) {
        viewModel.setBaseRate(baseRate)
    }
}
