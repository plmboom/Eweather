package com.example.projectsix_eweather.OthersH

import com.example.projectsix_eweather.Api.Weather.WeatherApi
import com.example.projectsix_eweather.Api.Weather.WeatherResponse
import com.example.projectsix_eweather.BuildConfig
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val api: WeatherApi) {

    suspend fun getWeather (city: String): WeatherResponse {
        return api.getCurrentWeather(
            apiKey = BuildConfig.WEATHER_API_KEY,
            city = city
        )
    }
}