package com.sargis.khlopuzyan.data.remote.dto.response


import com.squareup.moshi.Json

data class Clouds(
    @field:Json(name = "all")
    val all: Int
)