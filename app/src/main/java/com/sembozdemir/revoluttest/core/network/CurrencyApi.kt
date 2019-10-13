package com.sembozdemir.revoluttest.core.network

import com.sembozdemir.revoluttest.core.network.model.CurrencyResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyApi {

    @GET("/latest")
    suspend fun getLatest(@Query("base") base: String): Response<CurrencyResponse>
}