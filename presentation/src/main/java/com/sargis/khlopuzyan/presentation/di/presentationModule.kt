package com.sargis.khlopuzyan.presentation.di

import com.sargis.khlopuzyan.presentation.ui.cityList.CityListViewModel
import com.sargis.khlopuzyan.presentation.ui.weather.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

private val viewModelModule = module {
    viewModel {
        WeatherViewModel(get(), get(), get())
    }

    viewModel {
        CityListViewModel(get())
    }
}

val presentationModule = listOf(viewModelModule)