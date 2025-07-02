package com.sargis.khlopuzyan.presentation.ui.cityList

import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.usecase.GetCityListUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class CityListViewModel(
    private val getCityListUseCase: GetCityListUseCase,
) : BaseViewModel<CityListUiState, CityListUiEvent>() {

    override val _uiState: MutableStateFlow<CityListUiState> = MutableStateFlow<CityListUiState>(
        CityListUiState()
    )

    override fun onEvent(event: CityListUiEvent) {
    }

    init {
        viewModelScope.launch {

            val cityList = getCityListUseCase.getCityInfo() ?: emptyList()

            updateUiState {
                it.copy(
                    cityList = cityList
                )
            }
        }
    }
}