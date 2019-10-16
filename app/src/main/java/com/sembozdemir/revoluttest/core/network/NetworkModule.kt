package com.sembozdemir.revoluttest.core.network

import com.sembozdemir.revoluttest.BuildConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import javax.inject.Singleton

private const val CURRENCY_API_BASE_URL = "https://revolut.duckdns.org"

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor()
        .setLevel(HttpLoggingInterceptor.Level.BODY)

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()

        if (BuildConfig.DEBUG) {
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }

        return okHttpBuilder.build()
    }

    @Provides
    @Singleton
    fun provideBigDecimalMoshiAdapter(): BigDecimalMoshiAdapter = BigDecimalMoshiAdapter()

    @Provides
    @Singleton
    fun provideMoshi(bigDecimalMoshiAdapter: BigDecimalMoshiAdapter): Moshi = Moshi.Builder()
        .add(bigDecimalMoshiAdapter)
        .build()

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit = Retrofit.Builder()
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi))
        .baseUrl(CURRENCY_API_BASE_URL)
        .build()

    @Provides
    @Singleton
    fun providePoiApi(retrofit: Retrofit): CurrencyApi = retrofit.create()
}