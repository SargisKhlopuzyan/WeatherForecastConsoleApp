package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.repository.WeatherRepository
import com.sargis.khlopuzyan.domain.util.Result

interface GetCityWeatherUseCase {
    suspend fun getCityWeather(cityId: Long): Result<WeatherInfo>
}

class GetCityWeatherUseCaseImpl(
    private val repository: WeatherRepository,
) : GetCityWeatherUseCase {
    override suspend fun getCityWeather(cityId: Long): Result<WeatherInfo> {
        return repository.getWeatherDataForCityId(cityId)
    }
}