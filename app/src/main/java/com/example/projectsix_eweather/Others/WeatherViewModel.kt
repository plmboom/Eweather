package com.example.projectsix_eweather.Others

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectsix_eweather.Api.Weather.RetrofitClient
import com.example.projectsix_eweather.Api.Weather.WeatherResponse
import kotlinx.coroutines.launch
import com.example.projectsix_eweather.BuildConfig

class WeatherViewModel: ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather




    private val _error = MutableLiveData<String>()
    val error: LiveData<String> = _error




    fun loadWeatherForCity (city: String){
        viewModelScope.launch {
            try {
                val result = RetrofitClient.weatherApi.getCurrentWeather(
                    apiKey = BuildConfig.WEATHER_API_KEY,
                    city = city
                )
                _weather.value = result
            }

            catch (e: Exception){
                _error.value = "Ошибка: ${e.message}"
            }
        }
    }
}