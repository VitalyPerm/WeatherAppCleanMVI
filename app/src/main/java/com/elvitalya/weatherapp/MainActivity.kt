package com.elvitalya.weatherapp

import android.Manifest
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.elvitalya.weatherapp.presentation.theme.DarkBlue
import com.elvitalya.weatherapp.presentation.theme.DeepBlue
import com.elvitalya.weatherapp.presentation.theme.WeatherAppTheme
import com.elvitalya.weatherapp.presentation.ui.WeatherScreen
import com.elvitalya.weatherapp.presentation.ui.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
        }
        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION,
            )
        )
        setContent {
            WeatherAppTheme {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(DeepBlue),
                    contentAlignment = Alignment.Center
                ) {
                    if (viewModel.state.isLoading) CircularProgressIndicator(color = Color.White)
                    else WeatherScreen(
                        state = viewModel.state,
                        backgroundColor = DeepBlue
                    )
                }
            }
        }
    }
}