package com.sargis.khlopuzyan.domain.util.temperature

sealed class TemperatureUnit(val value: String) {
    object CELSIUS : TemperatureUnit("°C")
    object FAHRENHEIT : TemperatureUnit("F")
}