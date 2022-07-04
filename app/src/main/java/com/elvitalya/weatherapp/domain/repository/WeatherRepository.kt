package com.elvitalya.weatherapp.domain.repository

import com.elvitalya.weatherapp.domain.util.Resource
import com.elvitalya.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, long: Double): Resource<WeatherInfo>
}