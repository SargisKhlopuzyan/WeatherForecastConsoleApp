package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.repository.WeatherRepository
import com.sargis.khlopuzyan.domain.util.Result

class GetCityWeatherUseCase(
    private val repository: WeatherRepository,
) {
    suspend operator fun invoke(cityId: Long): Result<WeatherInfo> {
        return repository.getWeatherDataForCityId(cityId)
    }
}