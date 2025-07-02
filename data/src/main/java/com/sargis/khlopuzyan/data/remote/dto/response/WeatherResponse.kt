package com.sargis.khlopuzyan.data.remote.dto.response

import com.squareup.moshi.Json

data class WeatherResponse(
    @field:Json(name = "id")
    val id: Long,

    @field:Json(name = "name")
    val name: String,

    @field:Json(name = "main")
    val main: Main,
    @field:Json(name = "wind")
    val wind: Wind,

    @field:Json(name = "sys")
    val sys: Sys,

    @field:Json(name = "timezone")
    val timezone: Int,

    @field:Json(name = "weather")
    val weather: List<Weather>,

    @field:Json(name = "clouds")
    val clouds: Clouds,
)