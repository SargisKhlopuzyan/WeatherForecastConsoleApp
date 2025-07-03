package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.domain.repository.CityRepository

class GetCityListUseCase(
    private val repository: CityRepository,
) {
    suspend operator fun invoke(): List<CityInfo>? {
        return repository.getCityList()
    }
}