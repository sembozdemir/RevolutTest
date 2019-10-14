package com.sembozdemir.revoluttest.main

import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.base.BaseActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity<MainViewModel>() {

    private val currenciesRecyclerAdapter = CurrenciesRecyclerAdapter()

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel()= provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setupRecyclerView()

        observeState()

        viewModel.startFetchCurrencies()
    }

    private fun setupRecyclerView() {
        mainRecyclerView.layoutManager = StaggeredGridLayoutManager(1, StaggeredGridLayoutManager.VERTICAL)
        mainRecyclerView.adapter = currenciesRecyclerAdapter
        mainRecyclerView.setHasFixedSize(true)
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

    private fun showSuccess(data: MainUIModel) {
        mainSwipeRefreshLayout.isRefreshing = false
        currenciesRecyclerAdapter.updateItems(data.items)

    }

    private fun showError(message: String) {
        mainSwipeRefreshLayout.isRefreshing = false
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }
}
