package com.sargis.khlopuzyan.domain.entity.city


data class CityInfo(
    val coord: Coord,
    val country: String,
    val _id: Long,
    val name: String,
)

fun CityInfo.getDisplayName()=  "$name, $country, Location: latitude: ${coord.lat}, longitude ${coord.lon}"