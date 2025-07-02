package com.sargis.khlopuzyan.presentation.ui.weather

import com.sargis.khlopuzyan.presentation.base.UIEvent

sealed interface WeatherUiEvent : UIEvent {
    object TryAgain : WeatherUiEvent
}