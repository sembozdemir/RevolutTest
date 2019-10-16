package com.sembozdemir.revoluttest.core.extensions

import java.math.BigDecimal
import java.text.DecimalFormat

fun Int?.orZero() = this ?: 0

fun Double?.orZero() = this ?: 0.0

fun Float?.orZero() = this ?: 0f

fun BigDecimal?.orZero() = this ?: BigDecimal.ZERO

const val DEFAULT_DECIMAL_FORMAT = "###,##0.00"

fun BigDecimal.toFormattedString(format: String = DEFAULT_DECIMAL_FORMAT): String = DecimalFormat(format).format(this)