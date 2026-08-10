package com.example.projectsix_eweather

data class UnsplashResponse(
    val photos: List<UnsplashPhoto>
)

data class UnsplashPhoto(
    val src: UnsplashUrls
)

data class UnsplashUrls(
    val large: String,
    val medium: String
)