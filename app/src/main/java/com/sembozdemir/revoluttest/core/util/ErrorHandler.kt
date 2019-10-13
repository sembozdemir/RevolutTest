package com.sembozdemir.revoluttest.core.util

interface ErrorHandler {
    fun getPrettyMessage(e: Exception): String
    fun getEmptyResponseMessage(): String
}