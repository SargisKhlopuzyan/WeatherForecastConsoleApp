package com.sargis.khlopuzyan.data.di

import com.sargis.khlopuzyan.data.remote.WeatherApiDataSource
import com.sargis.khlopuzyan.data.remote.WeatherApiDataSourceImpl
import com.sargis.khlopuzyan.data.remote.WeatherApiRetrofitBuilder
import com.sargis.khlopuzyan.data.repository.CityRepositoryImpl
import com.sargis.khlopuzyan.data.repository.WeatherRepositoryImpl
import com.sargis.khlopuzyan.data.util.JsonConverter
import com.sargis.khlopuzyan.domain.repository.CityRepository
import com.sargis.khlopuzyan.domain.repository.WeatherRepository
import com.sargis.khlopuzyan.domain.util.temperature.TemperatureConverter
import com.sargis.khlopuzyan.domain.util.temperature.TemperatureConverterImpl
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedConverter
import com.sargis.khlopuzyan.domain.util.wind.WindSpeedConverterImpl
import org.koin.dsl.module

private val repositoryModule = module {
    single<WeatherApiDataSource> { WeatherApiDataSourceImpl(get()) }
    single<WeatherRepository> { WeatherRepositoryImpl(get(), get(), get(), get(), get()) }
    single<CityRepository> { CityRepositoryImpl(get()) }
    single { WeatherApiRetrofitBuilder.build() }
}

private val utilsModule = module {
    single<TemperatureConverter> { TemperatureConverterImpl() }
    single<WindSpeedConverter> { WindSpeedConverterImpl() }
    single<JsonConverter> { JsonConverter(get()) }
}

val dataModule = listOf(repositoryModule, utilsModule)