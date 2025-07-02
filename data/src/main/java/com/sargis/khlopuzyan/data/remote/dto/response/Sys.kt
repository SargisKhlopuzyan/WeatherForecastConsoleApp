package com.sargis.khlopuzyan.data.remote.dto.response


import com.squareup.moshi.Json

data class Sys(
    @field:Json(name = "country")
    val country: String,
    @field:Json(name = "id")
    val id: Int,
    @field:Json(name = "message")
    val message: Double,
    @field:Json(name = "sunrise")
    val sunrise: Long,
    @field:Json(name = "sunset")
    val sunset: Long,
    @field:Json(name = "type")
    val type: Int
)