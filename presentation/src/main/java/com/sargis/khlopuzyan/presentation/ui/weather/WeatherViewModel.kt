package com.sargis.khlopuzyan.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.usecase.GetCityWeatherUseCase
import com.sargis.khlopuzyan.domain.usecase.UnitsMeasurementUseCase
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val cityId: Long,
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val unitsMeasurementUseCase: UnitsMeasurementUseCase,
) : BaseViewModel<WeatherUiState, WeatherUiEvent>() {

    override val _uiState: MutableStateFlow<WeatherUiState> =
        MutableStateFlow<WeatherUiState>(WeatherUiState.Loading)

    override val uiState: StateFlow<WeatherUiState> = _uiState.onStart {
        fetchWeatherInfo()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        WeatherUiState.Loading
    )

    override fun onEvent(event: WeatherUiEvent) {
        when (event) {
            WeatherUiEvent.TryAgain -> fetchWeatherInfo()
        }
    }

    private fun fetchWeatherInfo() {
        viewModelScope.launch {
            updateUiState(WeatherUiState.Loading)
            val result = getCityWeatherUseCase(cityId)

            when (result) {
                is Result.Error<WeatherInfo> -> {
                    updateUiState(WeatherUiState.Error)
                }

                is Result.Success<WeatherInfo> -> {
                    updateUiState(
                        WeatherUiState.Success(
                            temperatureUnit = unitsMeasurementUseCase.getTemperatureUnit(),
                            windSpeedUnit = unitsMeasurementUseCase.getWindSpeedUnit(),
                            weatherInfo = result.data
                        )
                    )
                }
            }
        }
    }
}