package com.sargis.khlopuzyan.presentation.ui.cityList

import com.sargis.khlopuzyan.presentation.base.UIEvent

sealed interface CityListUiEvent : UIEvent {
    data class OnLatitudeChanged(val latitude: String) : CityListUiEvent
    data class OnLongitudeChanged(val longitude: String) : CityListUiEvent
}