package com.sargis.khlopuzyan.presentation.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.sargis.khlopuzyan.presentation.ui.cityList.CityListScreen
import com.sargis.khlopuzyan.presentation.ui.weather.WeatherScreen
import com.sargis.khlopuzyan.presentation.ui.weather.WeatherViewModel
import org.koin.androidx.compose.koinViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun WeatherNavigation() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Screen.CityListScreen.route
    ) {
        composable(route = Screen.CityListScreen.route) {
            CityListScreen(navController)
        }
        composable(
            route = Screen.WeatherScreen.route + "?cityId={cityId}",
            arguments = listOf(
                navArgument("cityId") {
                    type = NavType.LongType
                    defaultValue = -1
                    nullable = false
                }
            )
        ) { backStackEntry ->
            val cityId = backStackEntry.arguments?.getLong("cityId") ?: -1
            koinViewModel<WeatherViewModel>(
                parameters = { parametersOf(cityId) }
            )
            WeatherScreen(navController)
        }
    }
}