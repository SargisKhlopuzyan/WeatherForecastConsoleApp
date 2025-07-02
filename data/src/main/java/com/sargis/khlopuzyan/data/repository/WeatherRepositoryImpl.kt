package com.sargis.khlopuzyan.data.repository

import com.sargis.khlopuzyan.data.remote.WeatherApiDataSource
import com.sargis.khlopuzyan.domain.entity.WeatherInfo
import com.sargis.khlopuzyan.domain.repository.WeatherRepository
import com.sargis.khlopuzyan.domain.usecase.UnitsMeasurementUseCase
import com.sargis.khlopuzyan.domain.util.Result
import com.sargis.khlopuzyan.domain.util.temperature.TemperatureConverter
import com.sargis.khlopuzyan.domain.util.time.TimeConverter
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedConverter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherRepositoryImpl(
    private val weatherSource: WeatherApiDataSource,
    private val temperatureConverter: TemperatureConverter,
    private val windSpeedConverter: WindSpeedConverter,
    private val timeConverter: TimeConverter,
    private val unitsMeasurementUseCase: UnitsMeasurementUseCase,
) : WeatherRepository {

    override suspend fun getWeatherDataForCityId(id: Long): Result<WeatherInfo> {
        return withContext(Dispatchers.IO) {
            val result = weatherSource.getWeatherForLocation(id)
            result.data?.let {
                Result.Success(
                    data = WeatherInfo(
                        id = it.id,
                        name = it.name,
                        // weather
                        weatherInfo = it.weather.map { it.main },
                        temperature = temperatureConverter.getTemperature(
                            unitsMeasurementUseCase.getTemperatureUnit(),
                            it.main.temp
                        ),
                        // main
                        pressure = it.main.pressure,
                        humidity = it.main.humidity,
                        minTemperature = temperatureConverter.getTemperature(
                            unitsMeasurementUseCase.getTemperatureUnit(),
                            it.main.tempMin
                        ),
                        maxTemperature = temperatureConverter.getTemperature(
                            unitsMeasurementUseCase.getTemperatureUnit(),
                            it.main.tempMax
                        ),
                        // wind
                        windSpeed = windSpeedConverter.getWindSpeed(
                            unitsMeasurementUseCase.getWindSpeedUnit(),
                            it.wind.speed
                        ),
                        windDeg = it.wind.deg,
                        //clouds
                        clouds = it.clouds.all,

                        sunriseTime = timeConverter.secondsSinceEpochToLocalTime(
                            it.sys.sunrise,
                            it.timezone
                        ),
                        sunsetTime = timeConverter.secondsSinceEpochToLocalTime(
                            it.sys.sunset,
                            it.timezone
                        ),
                    )
                )
            } ?: run {
                Result.Error(result.message)
            }
        }
    }
}