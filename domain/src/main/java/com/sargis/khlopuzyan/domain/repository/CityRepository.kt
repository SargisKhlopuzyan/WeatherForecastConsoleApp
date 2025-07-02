package com.sargis.khlopuzyan.domain.repository

import com.sargis.khlopuzyan.domain.entity.city.CityInfo

interface CityRepository {
    suspend fun getCityList(): List<CityInfo>?
}