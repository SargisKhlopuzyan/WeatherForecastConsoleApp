package com.sargis.khlopuzyan.domain.util.temperature

interface TemperatureConverter {
    fun kelvinToCelsius(kelvin: Double): Double
    fun celsiusToKelvin(celsius: Double): Double
    fun kelvinToFahrenheit(kelvin: Double): Double
    fun fahrenheitToKelvin(fahrenheit: Double): Double
    fun celsiusToFahrenheit(celsius: Double): Double
    fun fahrenheitToCelsius(fahrenheit: Double): Double
    fun getTemperature(unit: String, value: Double): Double
    fun getTemperatureUnitLabel(unit: String): String
}

class TemperatureConverterImpl : TemperatureConverter {
    override fun kelvinToCelsius(kelvin: Double): Double {
        return kelvin - 273.15
    }

    override fun celsiusToKelvin(celsius: Double): Double {
        return celsius + 273.15
    }

    override fun kelvinToFahrenheit(kelvin: Double): Double {
        return (kelvin - 273.15) * 9 / 5 + 32
    }

    override fun fahrenheitToKelvin(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9 + 273.15
    }

    override fun celsiusToFahrenheit(celsius: Double): Double {
        return celsius * 9 / 5 + 32
    }

    override fun fahrenheitToCelsius(fahrenheit: Double): Double {
        return (fahrenheit - 32) * 5 / 9
    }

    override fun getTemperature(unit: String, value: Double): Double {
        return when (unit) {
            TemperatureUnit.CELSIUS.value -> kelvinToCelsius(value)
            TemperatureUnit.FAHRENHEIT.value -> kelvinToFahrenheit(value)
            else -> value
        }
    }

    override fun getTemperatureUnitLabel(unit: String): String {
        return when (unit) {
            TemperatureUnit.CELSIUS.value -> "C"
            TemperatureUnit.FAHRENHEIT.value -> "F"
            else -> ""
        }
    }
}
