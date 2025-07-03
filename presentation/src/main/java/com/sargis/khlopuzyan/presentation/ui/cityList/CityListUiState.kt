package com.sargis.khlopuzyan.presentation.ui.cityList

import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.presentation.base.UIState

data class CityListUiState(
    var cityList: List<CityInfo> = emptyList(),
    var loading: Boolean = false,
) : UIState