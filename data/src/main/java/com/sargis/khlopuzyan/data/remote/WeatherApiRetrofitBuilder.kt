package com.sargis.khlopuzyan.data.remote

import com.sargis.khlopuzyan.data.BuildConfig
import com.sargis.khlopuzyan.domain.constants.CONNECT_TIMEOUT_IN_SECONDS
import com.sargis.khlopuzyan.domain.constants.READ_TIMEOUT_IN_SECONDS
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create
import java.util.concurrent.TimeUnit

object WeatherApiRetrofitBuilder {

    private const val WEATHER_BASE_URL = "https://api.openweathermap.org/"

    fun build(): WeatherApi {
        val okHttpClient = OkHttpClient.Builder().apply {
            addInterceptor(ConnectivityInterceptor())
            connectTimeout(CONNECT_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)
            readTimeout(READ_TIMEOUT_IN_SECONDS, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG) {
                HttpLoggingInterceptor().also {
                    it.level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(it)
                }
            }
        }.build()

        return Retrofit.Builder()
            .baseUrl(WEATHER_BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create()
    }
}