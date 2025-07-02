package com.sargis.khlopuzyan.domain.util.wind

sealed class WindSpeedUnit(val value: String) {
    object METERS_PER_SECOND : WindSpeedUnit("mps")
    object KILOMETRES_PER_HOUR : WindSpeedUnit("kph")
    object MILES_PER_HOUR : WindSpeedUnit("mph")
}
