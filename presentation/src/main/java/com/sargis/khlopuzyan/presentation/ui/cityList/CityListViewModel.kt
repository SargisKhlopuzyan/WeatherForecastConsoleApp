package com.sargis.khlopuzyan.presentation.ui.cityList

import androidx.lifecycle.viewModelScope
import com.sargis.khlopuzyan.domain.entity.city.Coord
import com.sargis.khlopuzyan.domain.usecase.GetCityListUseCase
import com.sargis.khlopuzyan.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlin.math.sqrt

class CityListViewModel(
    private val getCityListUseCase: GetCityListUseCase,
) : BaseViewModel<CityListUiState, CityListUiEvent>() {

    override val _uiState: MutableStateFlow<CityListUiState> =
        MutableStateFlow<CityListUiState>(CityListUiState())

    override val uiState: StateFlow<CityListUiState> = _uiState.onStart {
        constructInputFieldsChange()
        loadCityWeather()
    }.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        CityListUiState(loading = true)
    )

    private val latitudeState: MutableStateFlow<String> = MutableStateFlow<String>("")
    private val longitudeState: MutableStateFlow<String> = MutableStateFlow<String>("")

    override fun onEvent(event: CityListUiEvent) {
        when (event) {
            is CityListUiEvent.OnLatitudeChanged -> {
                latitudeState.tryEmit(event.latitude)
            }

            is CityListUiEvent.OnLongitudeChanged -> {
                longitudeState.tryEmit(event.longitude)
            }
        }
    }

    private fun constructInputFieldsChange() {
        combine(
            latitudeState,
            longitudeState,
        ) { lat, long ->
            updateUiState(uiState.value.copy(loading = true))
        }.debounce(500)
            .flatMapLatest { it ->
                flow {
                    emit(it)
                }
            }
            .flowOn(Dispatchers.IO)
            .onEach {
                sortCityListByCoordinate()
            }
            .launchIn(viewModelScope)
    }

    private fun loadCityWeather() {
        viewModelScope.launch {
            updateUiState(uiState.value.copy(loading = true))
            val cityList = getCityListUseCase() ?: emptyList()
            updateUiState(
                uiState.value.copy(
                    cityList = cityList,
                    loading = false
                )
            )
        }
    }

    private fun sortCityListByCoordinate() {
        viewModelScope.launch(Dispatchers.IO) {
            updateUiState(uiState.value.copy(loading = true))
            try {
                val lat = latitudeState.value.toDouble()
                val lon = longitudeState.value.toDouble()
                _uiState.update {
                    uiState.value.copy(
                        loading = false,
                        cityList = uiState.value.cityList.sortedBy {
                            calculateDistance(it.coord, Coord(lat, lon))
                        }
                    )
                }
            } catch (e: Exception) {
                updateUiState(uiState.value.copy(loading = false))
            }
        }
    }

    private fun calculateDistance(coordinate1: Coord, coordinate2: Coord): Double {
        return sqrt(
            (coordinate1.lat - coordinate2.lat)
                    * (coordinate1.lat - coordinate2.lat)
                    + (coordinate1.lon - coordinate2.lon)
                    * (coordinate1.lon - coordinate2.lon)
        )
    }
}