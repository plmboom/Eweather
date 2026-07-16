package com.example.projectsix_eweather.Api

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi{
    @GET("current.json")
        fun getCurrentWeather(
        @Query("key") apiKey: String,
        @Query("q") city: String
        ): Call<WeatherResponse>

}