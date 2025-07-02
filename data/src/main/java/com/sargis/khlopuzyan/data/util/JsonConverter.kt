package com.sargis.khlopuzyan.data.util

import android.content.Context
import com.google.gson.GsonBuilder
import com.sargis.khlopuzyan.domain.entity.city.CityInfo

class JsonConverter(val context: Context) {
    fun parsJsonToListOfCity(): List<CityInfo>? {
        val jsonString =
            context.assets.open("city_list.json").bufferedReader().use { it.readText() }
        val gsonBuilder = GsonBuilder()
        val gson = gsonBuilder.create()
        return gson.fromJson(jsonString, Array<CityInfo>::class.java).toList()
    }
}