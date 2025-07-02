package com.sargis.khlopuzyan.presentation.ui.weather

import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.usecase.GetCityWeatherUseCase
import com.sargis.khlopuzyan.domain.usecase.UnitsMeasurementUseCase
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch

class WeatherViewModel(
    private val cityId: Long,
    private val getCityWeatherUseCase: GetCityWeatherUseCase,
    private val unitsMeasurementUseCase: UnitsMeasurementUseCase,
) : BaseViewModel<WeatherUiState, WeatherUiEvent>() {

    override val _uiState: MutableStateFlow<WeatherUiState> = MutableStateFlow<WeatherUiState>(
        WeatherUiState()
    )

    override fun onEvent(event: WeatherUiEvent) {
        when (event) {
            WeatherUiEvent.TryAgain -> fetchWeatherInfo()
        }
    }

    init {
        fetchWeatherInfo()
    }

    fun fetchWeatherInfo() {
        viewModelScope.launch {
            updateUiState {
                it.copy(
                    isLoading = true,
                    shoError = false
                )
            }
            val result = getCityWeatherUseCase.getCityWeather(cityId)

            when (result) {
                is Result.Error<WeatherInfo> -> {
                    updateUiState {
                        it.copy(
                            shoError = true,
                            isLoading = false
                        )
                    }
                }

                is Result.Success<WeatherInfo> -> {
                    updateUiState {
                        it.copy(
                            shoError = false,
                            isLoading = false,
                            temperatureUnit = unitsMeasurementUseCase.getTemperatureUnit(),
                            windSpeedUnit = unitsMeasurementUseCase.getWindSpeedUnit(),
                            weatherInfo = result.data
                        )
                    }
                }
            }
        }
    }
}