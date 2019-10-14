package com.sembozdemir.revoluttest.main

sealed class MainState {
    class Success(val data: MainUIModel): MainState()
    class Error(val message: String): MainState()
    object Loading : MainState()
}