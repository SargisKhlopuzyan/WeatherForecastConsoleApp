package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.util.Result

interface WeatherRepository {
    suspend fun getWeatherDataForCityId(id: Long): Result<WeatherInfo>
}