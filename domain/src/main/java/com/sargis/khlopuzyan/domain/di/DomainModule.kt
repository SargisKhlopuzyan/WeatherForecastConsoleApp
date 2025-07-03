package com.sargis.khlopuzyan.domain.di

import com.sargis.khlopuzyan.domain.usecase.GetCityListUseCase
import com.sargis.khlopuzyan.domain.usecase.GetCityWeatherUseCase
import com.sargis.khlopuzyan.domain.usecase.UnitsMeasurementUseCase
import com.sargis.khlopuzyan.domain.usecase.UnitsMeasurementUseCaseImpl
import com.sargis.khlopuzyan.domain.util.temperature.TemperatureConverter
import com.sargis.khlopuzyan.domain.util.temperature.TemperatureConverterImpl
import com.sargis.khlopuzyan.domain.util.time.TimeConverter
import com.sargis.khlopuzyan.domain.util.time.TimeConverterImpl
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedConverter
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedConverterImpl
import org.koin.dsl.module

private val useCaseModule = module {
    single<GetCityWeatherUseCase> { GetCityWeatherUseCase(get()) }
    single<GetCityListUseCase> { GetCityListUseCase(get()) }
    single<UnitsMeasurementUseCase> { UnitsMeasurementUseCaseImpl() }
}

private val utilsModule = module {
    single<TemperatureConverter> { TemperatureConverterImpl() }
    single<WindSpeedConverter> { WindSpeedConverterImpl() }
    single<TimeConverter> { TimeConverterImpl() }
}

val domainModule = listOf(useCaseModule, utilsModule)