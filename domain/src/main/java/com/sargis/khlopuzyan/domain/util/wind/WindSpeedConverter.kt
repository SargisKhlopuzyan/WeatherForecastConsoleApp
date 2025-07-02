package com.sargis.khlopuzyan.domain.util.wind

interface WindSpeedConverter {
    fun metersPerSecondToMilesPerHour(metersPerSecond: Double): Double
    fun metersPerSecondToKilometresPerHour(metersPerSecond: Double): Double
    fun milesPerHourToMetersPerSecond(milesPerHour: Double): Double
    fun getWindSpeed(unit: String, value: Double): Double
    fun getWindSpeedUnitLabel(unit: String): String
}

class WindSpeedConverterImpl : WindSpeedConverter {

    override fun metersPerSecondToKilometresPerHour(metersPerSecond: Double): Double {
        return (metersPerSecond * METERS_PER_SECOND_TO_KILOMETRES_PER_HOUR)
    }

    override fun metersPerSecondToMilesPerHour(metersPerSecond: Double): Double {
        return (metersPerSecond * METERS_PER_SECOND_TO_MILES_PER_HOUR)
    }

    override fun milesPerHourToMetersPerSecond(milesPerHour: Double): Double {
        return (milesPerHour * MILES_PER_HOUR_TO_METERS_PER_SECOND)
    }

    override fun getWindSpeed(unit: String, value: Double): Double {
        return when (unit) {
            WindSpeedUnit.METERS_PER_SECOND.value -> value
            WindSpeedUnit.KILOMETRES_PER_HOUR.value -> metersPerSecondToKilometresPerHour(value)
            WindSpeedUnit.MILES_PER_HOUR.value -> metersPerSecondToMilesPerHour(value)
            else -> value
        }
    }

    override fun getWindSpeedUnitLabel(unit: String): String {
        return when (unit) {
            WindSpeedUnit.METERS_PER_SECOND.value -> "mps"
            WindSpeedUnit.KILOMETRES_PER_HOUR.value -> "kph"
            WindSpeedUnit.MILES_PER_HOUR.value -> "mph"
            else -> ""
        }
    }

    companion object {
        private const val METERS_PER_SECOND_TO_KILOMETRES_PER_HOUR = 0.001
        private const val MILES_PER_HOUR_TO_METERS_PER_SECOND = 0.44704
        private const val METERS_PER_SECOND_TO_MILES_PER_HOUR = 2.23694
    }
}
