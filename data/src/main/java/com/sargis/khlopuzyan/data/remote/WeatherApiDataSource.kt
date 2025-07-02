package com.sargis.khlopuzyan.data.remote

import com.sargis.khlopuzyan.data.remote.dto.response.WeatherResponse
import com.sargis.khlopuzyan.data.util.OPEN_WEATHER_API_KEY
import com.sargis.khlopuzyan.domain.util.Result

interface WeatherApiDataSource {
    suspend fun getWeatherForLocation(id: Long): Result<WeatherResponse>
}

class WeatherApiDataSourceImpl(
    private val api: WeatherApi,
) : WeatherApiDataSource {
    override suspend fun getWeatherForLocation(id: Long): Result<WeatherResponse> {
        return try {
            Result.Success(
                data = api.getWeatherData(
                    id = id,
                    appId = OPEN_WEATHER_API_KEY
                )
            )
        } catch (e: Exception) {
            Result.Error(e.message)
        }
    }
}