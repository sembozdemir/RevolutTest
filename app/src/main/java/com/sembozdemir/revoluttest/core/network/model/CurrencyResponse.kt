package com.sembozdemir.revoluttest.core.network.model

import com.squareup.moshi.Json

data class CurrencyResponse(

	@field:Json(name="date")
	val date: String? = null,

	@field:Json(name="rates")
	val rates: Rates? = null,

	@field:Json(name="base")
	val base: String? = null
)