package com.example.projectsix_eweather.Api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object UnsplashRetrofitClient {
    val unsplashApi: CityApi by lazy {
        Retrofit.Builder()
            .baseUrl("https://api.pexels.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CityApi::class.java)
    }
}