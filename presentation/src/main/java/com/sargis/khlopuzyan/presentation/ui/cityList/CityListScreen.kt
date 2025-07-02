package com.sargis.khlopuzyan.presentation.ui.cityList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.domain.entity.city.getDisplayName
import com.sargis.khlopuzyan.presentation.ui.navigation.Screen
import com.sargis.khlopuzyan.presentation.ui.theme.WeatherForecastConsoleAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CityListScreen(
    navController: NavController,
    viewModel: CityListViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    if (uiState.cityList.isNotEmpty()) {
        CityList(uiState) { cityInfo ->
            navController.navigate(
                Screen.WeatherScreen.route + "?cityId=${cityInfo._id}"
            )
        }
    }
}

@Composable
fun CityList(uiState: CityListUiState, onItemClicked: (CityInfo) -> Unit) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(innerPadding)
                    .padding(16.dp)
            ) {
                items(uiState.cityList) { cityInfo ->
                    CityItem(
                        modifier = Modifier
                            .fillMaxWidth()
                            .wrapContentHeight()
                            .clickable {
                                onItemClicked(cityInfo)
                            }
                            .padding(vertical = 12.dp),
                        cityInfo = cityInfo
                    )
                }
            }
        }
    }
}

@Composable
fun CityItem(modifier: Modifier, cityInfo: CityInfo) {
    Text(
        modifier = modifier,
        text = cityInfo.getDisplayName(),
        style = MaterialTheme.typography.titleMedium,
        color = Color.DarkGray,
    )
}


@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherForecastConsoleAppTheme {
        CityList(CityListUiState()) {}
    }
}