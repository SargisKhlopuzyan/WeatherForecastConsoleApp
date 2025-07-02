package com.sargis.khlopuzyan.domain.util.time

import java.time.LocalDateTime
import java.time.LocalTime
import java.time.ZoneOffset

interface TimeConverter {
    fun secondsSinceEpochToLocalTime(
        secondsSinceEpoch: Long,
        timezoneShiftInSecFromUTC: Int,
    ): LocalTime
}

class TimeConverterImpl : TimeConverter {
    override fun secondsSinceEpochToLocalTime(
        secondsSinceEpoch: Long,
        timezoneShiftInSecFromUTC: Int,
    ): LocalTime {
        val sunriseTime = LocalDateTime.ofEpochSecond(secondsSinceEpoch, 0, ZoneOffset.UTC)
        val localOffset = ZoneOffset.ofTotalSeconds(timezoneShiftInSecFromUTC)
        return sunriseTime.atOffset(ZoneOffset.UTC).atZoneSameInstant(localOffset).toLocalTime()
    }
}
