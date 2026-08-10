package com.example.projectsix_eweather.Api.Weather

import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
    @GET("current.json")
        suspend fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
        ): WeatherResponse

}