package com.sargis.khlopuzyan.presentation.ui.weather

import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.presentation.base.UIState

sealed interface WeatherUiState : UIState {
    object Loading : WeatherUiState
    object Error : WeatherUiState
    data class Success(
        val weatherInfo: WeatherInfo?,
        val temperatureUnit: String,
        val windSpeedUnit: String,
    ) : WeatherUiState
}