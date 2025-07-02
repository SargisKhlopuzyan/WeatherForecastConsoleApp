package com.sargis.khlopuzyan.presentation.ui.weather

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sargis.khlopuzyan.presentation.ui.DisplayTimeFormatHelper
import com.sargis.khlopuzyan.presentation.ui.theme.WeatherForecastConsoleAppTheme
import org.koin.androidx.compose.koinViewModel
import java.time.LocalTime
import kotlin.math.round

@Composable
fun WeatherScreen(
    navController: NavController,
    viewModel: WeatherViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    Weather(uiState, onTryAgain = {
        viewModel.onEvent(WeatherUiEvent.TryAgain)
    })
}

@Composable
fun Weather(uiState: WeatherUiState, onTryAgain: () -> Unit) {
    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .padding(innerPadding),
            contentAlignment = Alignment.Center
        ) {
            if (uiState.isLoading) {
                Loading()
            } else if (uiState.shoError) {
                Error(onTryAgain)
            } else if (uiState.weatherInfo != null) {
                WeatherInfo(uiState)
            }
        }
    }
}

@Composable
fun Loading() {
    Text(
        style = MaterialTheme.typography.titleMedium,
        text = "Loading..."
    )
}

@Composable
fun Error(onTryAgain: () -> Unit) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            style = MaterialTheme.typography.titleMedium,
            text = "Something went wrong, please try again"
        )
        Button(
            modifier = Modifier.padding(top = 16.dp),
            onClick = {
                onTryAgain()
            }) {
            Text(
                style = MaterialTheme.typography.bodyMedium,
                text = "Try again"
            )
        }
    }
}

@Composable
fun WeatherInfo(uiState: WeatherUiState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 16.dp),
            style = MaterialTheme.typography.titleLarge,
            color = Color.Black,
            text = "Weather for ${uiState.weatherInfo?.name}"
        )
        WeatherItem("WeatherInfo", "${uiState.weatherInfo?.weatherInfo?.first()}")
        WeatherItem("Pressure", "${uiState.weatherInfo?.pressure}")
        WeatherItem("Humidity", "${uiState.weatherInfo?.humidity}")
        WeatherItem(
            "Temperature",
            "${uiState.weatherInfo?.temperature?.round(4)} ${uiState.temperatureUnit}"
        )
        WeatherItem(
            "MinTemperature",
            "${uiState.weatherInfo?.minTemperature?.round(4)} ${uiState.temperatureUnit}"
        )
        WeatherItem(
            "MaxTemperature",
            "${uiState.weatherInfo?.maxTemperature?.round(4)} ${uiState.temperatureUnit}"
        )
        WeatherItem(
            "WindSpeed",
            "${uiState.weatherInfo?.windSpeed?.round(4)} ${uiState.windSpeedUnit}"
        )
        WeatherItem("WindDeg", "${uiState.weatherInfo?.windDeg}")
        WeatherItem("Clouds", "${uiState.weatherInfo?.clouds}")
        WeatherItem("SunriseTime", "${getFormattedTime(uiState.weatherInfo?.sunriseTime)}")
        WeatherItem("SunsetTime", "${getFormattedTime(uiState.weatherInfo?.sunsetTime)}")
    }
}

fun Double.round(decimals: Int): Double {
    var multiplier = 1.0
    repeat(decimals) { multiplier *= 10 }
    return round(this * multiplier) / multiplier
}

@Composable
private fun getFormattedTime(localTime: LocalTime?): AnnotatedString {
    localTime ?: return AnnotatedString(text = "")
    return DisplayTimeFormatHelper.displayTime(
        time = localTime,
        is24hSystem = true
    )
}

@Composable
fun WeatherItem(label: String, value: String) {
    Row {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 8.dp),
            text = "$label : $value",
            style = MaterialTheme.typography.titleMedium,
            color = Color.DarkGray,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherForecastConsoleAppTheme {
        Weather(WeatherUiState(isLoading = true), onTryAgain = {})
    }
}