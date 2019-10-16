package com.sembozdemir.revoluttest.main

data class MainState(
    val data: MainUIModel? = null,
    val errorMessage: String? = null,
    val loading: Boolean = false,
    val scrollTop: Boolean = false
)