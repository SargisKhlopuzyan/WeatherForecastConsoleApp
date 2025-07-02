package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.util.JsonConverter
import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.domain.repository.CityRepository

class CityRepositoryImpl(
    private val jsonConverter: JsonConverter,
) : CityRepository {
    override suspend fun getCityList(): List<CityInfo>? {
        return jsonConverter.parsJsonToListOfCity()
    }
}