package com.sargis.khlopuzyan.domain.usecase

import com.sargis.khlopuzyan.domain.util.temperature.TemperatureUnit
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedUnit


interface UnitsMeasurementUseCase {
    fun getTemperatureUnit(): String
    fun getWindSpeedUnit(): String
}

// TODO -In case we want to save need to inject shared pref repository and save and fetch from it
class UnitsMeasurementUseCaseImpl() : UnitsMeasurementUseCase {

    override fun getTemperatureUnit(): String {
        return TemperatureUnit.CELSIUS.value
    }

    override fun getWindSpeedUnit(): String {
        return WindSpeedUnit.KILOMETRES_PER_HOUR.value
    }
}
