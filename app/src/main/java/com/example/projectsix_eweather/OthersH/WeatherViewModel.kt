package com.example.projectsix_eweather.OthersH

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.projectsix_eweather.Api.Weather.WeatherResponse
import com.example.projectsix_eweather.BuildConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(private val repository: WeatherRepository, private val photoRepository: PhotoRepository): ViewModel() {

    private val _weather = MutableLiveData<WeatherResponse>()
    val weather: LiveData<WeatherResponse> = _weather

    private val _cityPhoto = MutableLiveData<String>()
    val cityPhoto: LiveData<String> = _cityPhoto




    private val _error = MutableLiveData<String?>()
    val error: LiveData<String?> = _error




    fun loadWeatherForCity (city: String){
        viewModelScope.launch {
            try {
                val result = repository.getWeather(city)
                _weather.value = result
                _error.value = null
            }

            catch (e: Exception){
                _error.value = "Ошибка: ${e.message}"
            }
        }
    }

    fun loadPhotoCity(city: String){
        viewModelScope.launch {
            try {
                val result = photoRepository.getCityPhoto(query = city, apiKey = BuildConfig.PEXELS_CLIENT_ID)
                _cityPhoto.value = result.photos.first().src.large
            } catch (e: Exception) {
                _error.value = "Ошибка фото: ${e.message}"
            }
        }
    }


    suspend fun fetchCityPhotoUrl(city: String): String? {
        return try {
            val result = photoRepository.getCityPhoto(
                query = city,
                apiKey = BuildConfig.PEXELS_CLIENT_ID
            )
            result.photos.firstOrNull()?.src?.large
        } catch (e: Exception) {
            null
        }
    }
}