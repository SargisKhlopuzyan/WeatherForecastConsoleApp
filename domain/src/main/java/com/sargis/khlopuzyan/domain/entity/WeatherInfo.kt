package com.sargis.khlopuzyan.domain.entity

import java.time.LocalTime

data class WeatherInfo(
    val id: Long,
    val name: String,

    // weather
    val weatherInfo: List<String>?,

    // main
    val temperature: Double,
    val pressure: Int,
    val humidity: Int,
    val minTemperature: Double,
    val maxTemperature: Double,

    // wind
    val windSpeed: Double,
    val windDeg: Int,

    // clouds
    val clouds: Int,

    val sunriseTime: LocalTime,
    val sunsetTime: LocalTime,
)