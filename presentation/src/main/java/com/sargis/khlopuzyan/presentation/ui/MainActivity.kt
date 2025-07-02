package com.sargis.khlopuzyan.presentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.sargis.khlopuzyan.presentation.ui.navigation.WeatherNavigation
import com.sargis.khlopuzyan.presentation.ui.theme.WeatherForecastConsoleAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            WeatherForecastConsoleAppTheme {
                WeatherNavigation()
            }
        }
    }
}