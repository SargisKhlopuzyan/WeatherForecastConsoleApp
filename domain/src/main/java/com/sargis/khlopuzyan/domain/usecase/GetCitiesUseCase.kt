package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.domain.repository.CityRepository

interface GetCityListUseCase {
    suspend fun getCityInfo(): List<CityInfo>?
}

class GetCityListUseCaseImpl(
    private val repository: CityRepository,
) : GetCityListUseCase {
    override suspend fun getCityInfo(): List<CityInfo>? {
        return repository.getCityList()
    }
}