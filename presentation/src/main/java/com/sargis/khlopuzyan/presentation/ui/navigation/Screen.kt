package com.sargis.khlopuzyan.presentation.ui.navigation

sealed class Screen(val route: String) {
    object CityListScreen : Screen("city_list_screen")
    object WeatherScreen : Screen("weather_screen")
}