package com.sembozdemir.revoluttest.core.network.model

import com.squareup.moshi.Json
import java.math.BigDecimal

data class Rates(

	@field:Json(name="CHF")
	val chf: BigDecimal? = null,

	@field:Json(name="HRK")
	val hrk: BigDecimal? = null,

	@field:Json(name="MXN")
	val mxn: BigDecimal? = null,

	@field:Json(name="ZAR")
	val zar: BigDecimal? = null,

	@field:Json(name="INR")
	val inr: BigDecimal? = null,

	@field:Json(name="CNY")
	val cny: BigDecimal? = null,

	@field:Json(name="THB")
	val thb: BigDecimal? = null,

	@field:Json(name="AUD")
	val aud: BigDecimal? = null,

	@field:Json(name="ILS")
	val ils: BigDecimal? = null,

	@field:Json(name="KRW")
	val krw: BigDecimal? = null,

	@field:Json(name="JPY")
	val jpy: BigDecimal? = null,

	@field:Json(name="PLN")
	val pln: BigDecimal? = null,

	@field:Json(name="GBP")
	val gbp: BigDecimal? = null,

	@field:Json(name="IDR")
	val idr: BigDecimal? = null,

	@field:Json(name="HUF")
	val huf: BigDecimal? = null,

	@field:Json(name="PHP")
	val php: BigDecimal? = null,

	@field:Json(name="TRY")
	val `try`: BigDecimal? = null,

	@field:Json(name="RUB")
	val rub: BigDecimal? = null,

	@field:Json(name="HKD")
	val hkd: BigDecimal? = null,

	@field:Json(name="ISK")
	val isk: BigDecimal? = null,

	@field:Json(name="DKK")
	val dkk: BigDecimal? = null,

	@field:Json(name="CAD")
	val cad: BigDecimal? = null,

	@field:Json(name="MYR")
	val myr: BigDecimal? = null,

	@field:Json(name="USD")
	val usd: BigDecimal? = null,

	@field:Json(name="BGN")
	val bgn: BigDecimal? = null,

	@field:Json(name="NOK")
	val nok: BigDecimal? = null,

	@field:Json(name="RON")
	val ron: BigDecimal? = null,

	@field:Json(name="SGD")
	val sgd: BigDecimal? = null,

	@field:Json(name="CZK")
	val czk: BigDecimal? = null,

	@field:Json(name="SEK")
	val sek: BigDecimal? = null,

	@field:Json(name="NZD")
	val nzd: BigDecimal? = null,

	@field:Json(name="BRL")
	val brl: BigDecimal? = null,

	@field:Json(name="EUR")
	val eur: BigDecimal? = null
)