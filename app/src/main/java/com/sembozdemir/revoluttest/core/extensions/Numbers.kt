package com.sembozdemir.revoluttest.core.extensions

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Float?.orZero() = this ?: 0f