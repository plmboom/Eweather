package com.example.projectsix_eweather.Api.City

import com.example.projectsix_eweather.UnsplashResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

interface CityApi {
    @GET("v1/photos")
    suspend fun searchPhotos(
        @Header("Authorization") query: String,
        @Query("client_id") clientId: String,
        @Query("per_page") perPage: Int = 1
    ): UnsplashResponse

}