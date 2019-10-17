package com.sembozdemir.revoluttest.core.extensions

import java.math.BigDecimal
import java.text.DecimalFormat


fun BigDecimal?.orZero(): BigDecimal = this ?: BigDecimal.ZERO

const val DEFAULT_DECIMAL_FORMAT = "###,##0.00"

fun BigDecimal.toFormattedString(format: String = DEFAULT_DECIMAL_FORMAT): String = DecimalFormat(format).format(this)