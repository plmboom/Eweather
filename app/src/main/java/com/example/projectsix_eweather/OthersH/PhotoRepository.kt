package com.example.projectsix_eweather.OthersH

import com.example.projectsix_eweather.Api.City.CityApi
import javax.inject.Inject

class PhotoRepository @Inject constructor(private val api: CityApi) {
    suspend fun getCityPhoto (query: String, apiKey: String) = api.searchPhotos(apiKey = apiKey, query = query)
    }
