package com.sargis.khlopuzyan.data.remote.dto.response


import com.squareup.moshi.Json

data class Wind(
    @field:Json(name = "deg")
    val deg: Int,
    @field:Json(name = "speed")
    val speed: Double,
)