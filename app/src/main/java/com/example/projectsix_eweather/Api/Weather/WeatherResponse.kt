package com.example.projectsix_eweather.Api.Weather

//Данные из API

data class WeatherResponse (
    val location: Location,
    val current: Current,
)
data class Location (
    val name: String,
    val localtime: String
)

data class Current (
    val temp_c: Double,
    val feelslike_c: Double,
    val condition: Condition,
    val wind_dir: String,
    val wind_kph: Double,
    val wind_degree: Int,
    val uv: Double
    )

data class Condition (
    val text: String,
    val code: Int
)








