package com.sargis.khlopuzyan.presentation.ui.cityList

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.sargis.khlopuzyan.domain.entity.city.CityInfo
import com.sargis.khlopuzyan.presentation.ui.navigation.Screen
import com.sargis.khlopuzyan.presentation.ui.theme.WeatherForecastConsoleAppTheme
import org.koin.androidx.compose.koinViewModel

@Composable
fun CityListScreen(
    navController: NavController,
    viewModel: CityListViewModel = koinViewModel(),
) {
    val uiState by viewModel.uiState.collectAsStateWithLifecycle()
    CityList(
        uiState,
        onItemClicked = { cityInfo ->
            navController.navigate(
                Screen.WeatherScreen.route + "?cityId=${cityInfo._id}"
            )
        },
        onLatitudeTextChanged = { latitude ->
            viewModel.onEvent(CityListUiEvent.OnLatitudeChanged(latitude))
        },
        onLongitudeTextChanged = { longitude ->
            viewModel.onEvent(CityListUiEvent.OnLongitudeChanged(longitude))
        }
    )
}

@Composable
fun CityList(
    uiState: CityListUiState,
    onItemClicked: (CityInfo) -> Unit,
    onLatitudeTextChanged: (String) -> Unit,
    onLongitudeTextChanged: (String) -> Unit,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .padding(16.dp)
        ) {
            CoordinateItem(label = "Latitude", onTextChanged = { text ->
                onLatitudeTextChanged(text)
            })

            CoordinateItem(label = "Longitude", onTextChanged = { text ->
                onLongitudeTextChanged(text)
            })

            if (uiState.loading) {
                Loading()
            }

            LazyColumn(
                modifier = Modifier.fillMaxSize()
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
private fun CoordinateItem(
    label: String,
    onTextChanged: (String) -> Unit,
) {
    val pattern = rememberSaveable {
        Regex("^[-.0-9]+$")
    }

    var state by rememberSaveable {
        mutableStateOf("")
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 4.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            modifier = Modifier
                .requiredWidth(100.dp)
                .padding(end = 4.dp),
            text = "$label:",
            style = MaterialTheme.typography.titleMedium,
        )

        OutlinedTextField(
            modifier = Modifier
                .fillMaxWidth(),
            value = state,
            onValueChange = {
                if (it.isEmpty() || it.matches(pattern)) {
                    state = it
                    onTextChanged(state)
                }
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
            label = { Text("Enter $label") },
            placeholder = { Text("Enter $label") },
            singleLine = true,
        )
    }
}

@Composable
private fun CityItem(modifier: Modifier, cityInfo: CityInfo) {
    Text(
        modifier = modifier,
        text = cityInfo.displayName,
        style = MaterialTheme.typography.titleMedium,
    )
}

@Composable
private fun Loading() {
    Text(
        modifier = Modifier
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.titleMedium,
        text = "Loading..."
    )
}

@Preview(showBackground = true)
@Composable
fun WeatherScreenPreview() {
    WeatherForecastConsoleAppTheme {
        CityList(CityListUiState(loading = true), {}, {}, {})
    }
}