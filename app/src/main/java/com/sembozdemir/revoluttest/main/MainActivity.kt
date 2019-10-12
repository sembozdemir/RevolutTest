package com.sembozdemir.revoluttest.main

import android.os.Bundle
import android.widget.Toast
import com.sembozdemir.revoluttest.R
import com.sembozdemir.revoluttest.core.base.BaseActivity

class MainActivity : BaseActivity<MainViewModel>() {

    override fun getLayoutResId() = R.layout.activity_main

    override fun createViewModel()= provideViewModel<MainViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Toast.makeText(this, "Test", Toast.LENGTH_LONG).show()
    }
}
