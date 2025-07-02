package com.sargis.khlopuzyan.presentation.ui.weather

import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.presentation.base.UIState

data class WeatherUiState(
    val isLoading: Boolean = false,
    val shoError: Boolean = false,
    val weatherInfo: WeatherInfo? = null,
    val temperatureUnit: String = "",
    val windSpeedUnit: String = "",
) : UIState