package com.sembozdemir.revoluttest

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import com.sembozdemir.revoluttest.core.util.CurrencyResources
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CurrencyResourcesTest {

    val currencies = listOf(
        "CHF",
        "hRK",
        "Mxn",
        "zar",
        "inr",
        "cny",
        "thb",
        "aud",
        "ils",
        "krw",
        "jpy",
        "pln",
        "gbp",
        "idr",
        "huf",
        "php",
        "try",
        "rub",
        "hkd",
        "isk",
        "dkk",
        "cad",
        "myr",
        "usd",
        "bgn",
        "nok",
        "ron",
        "sgd",
        "czk",
        "sek",
        "nzd",
        "brl",
        "eur"
    )

    private lateinit var context: Context

    @Before
    fun setup() {
        context = ApplicationProvider.getApplicationContext<Context>()
    }

    @Test
    fun getNameResId_returns_proper_id() {

        val currencyResources = CurrencyResources(context)

        currencies.forEach {
            val expectedId = context.resources.getIdentifier(
                "currency_${it.toLowerCase()}",
                "string",
                context.packageName
            )

            val actualId = currencyResources.getNameResId(it)

            assertEquals(expectedId, actualId)
        }
    }

    @Test
    fun getFlagDrawableResId_returns_proper_id() {

        val currencyResources = CurrencyResources(context)

        currencies.forEach {
            val expectedId = context.resources.getIdentifier(
                "ic_flag_${it.toLowerCase()}",
                "drawable",
                context.packageName
            )

            val actualId = currencyResources.getFlagDrawableResId(it)

            assertEquals(expectedId, actualId)
        }
    }

    @Test
    fun getNameResId_returns_proper_id_for_unknown_currencies() {

        val unknownCurrency = "unknown"

        val currencyResources = CurrencyResources(context)

        val expectedId = context.resources.getIdentifier(
            "currency_unknown",
            "string",
            context.packageName
        )

        val actualId = currencyResources.getNameResId(unknownCurrency)

        assertEquals(expectedId, actualId)
    }

    @Test
    fun getFlagDrawableResId_returns_proper_id_for_unknown_currencies() {

        val unknownCurrency = "unknown"

        val currencyResources = CurrencyResources(context)

        val expectedId = context.resources.getIdentifier(
            "ic_flag_unknown",
            "drawable",
            context.packageName
        )

        val actualId = currencyResources.getFlagDrawableResId(unknownCurrency)

        assertEquals(expectedId, actualId)
    }
}