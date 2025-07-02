package com.sargis.khlopuzyan.presentation.ui

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import java.time.LocalTime
import java.time.format.DateTimeFormatter

object DisplayTimeFormatHelper {

    private const val HOUR_THRESHOLD_24H = 10
    private const val HOUR_UPPER_BOUND_24H = 23

    @Composable
    fun displayTime(
        hourFontStyle: SpanStyle = MaterialTheme.typography.labelLarge.copy(
            fontSize = 18.sp
        ).toSpanStyle(),
        amPmFontStyle: SpanStyle = MaterialTheme.typography.labelSmall.copy(
            fontSize = 14.sp
        ).toSpanStyle(),
        time: LocalTime,
        showMinutes: Boolean = true,
        is24hSystem: Boolean,
    ): AnnotatedString {
        return if (is24hSystem) {
            val pattern =
                if (time.hour in HOUR_THRESHOLD_24H..HOUR_UPPER_BOUND_24H) "HH:mm" else "H:mm"
            val formatter = DateTimeFormatter.ofPattern(pattern)
            buildAnnotatedString {
                withStyle(hourFontStyle) {
                    append(time.format(formatter))
                }
            }
        } else {
            val pattern = if (showMinutes) "h:mm a" else "h a"
            val formatter = DateTimeFormatter.ofPattern(pattern)
            val formattedTime = time.format(formatter)
            val amPmPart = formattedTime.takeLast(2)
            val timePart = formattedTime.dropLast(2).trim()

            buildAnnotatedString {
                withStyle(hourFontStyle) {
                    append(timePart)
                }
                append(" ")
                withStyle(amPmFontStyle) {
                    append(amPmPart)
                }
            }
        }
    }
}
