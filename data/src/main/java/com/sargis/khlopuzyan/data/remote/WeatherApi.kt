package com.sargis.khlopuzyan.data.remote

import com.sargis.khlopuzyan.data.remote.dto.response.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("id") id: Long,
        @Query("appid") appId: String,
    ): WeatherResponse
}