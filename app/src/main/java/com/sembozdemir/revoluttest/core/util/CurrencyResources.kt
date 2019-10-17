package com.sembozdemir.revoluttest.core.util

import android.content.Context
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import com.sembozdemir.revoluttest.R

class CurrencyResources(val context: Context) {

    @StringRes
    fun getNameResId(code: String): Int {
        val resId = context.resources.getIdentifier(
            "currency_${code.toLowerCase()}",
            "string",
            context.packageName)
        if (resId == 0) return R.string.currency_unknown
        return resId
    }

    @DrawableRes
    fun getFlagDrawableResId(code: String): Int {
        val resId = context.resources.getIdentifier(
            "ic_flag_${code.toLowerCase()}",
            "drawable",
            context.packageName
        )
        if (resId == 0) return R.drawable.ic_flag_unknown
        return resId
    }
}