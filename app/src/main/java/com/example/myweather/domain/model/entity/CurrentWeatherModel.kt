package com.example.myweather.domain.model.entity

data class CurrentWeatherModel(
    val isDay: Int,
    val temperature: Double,
    val time: String,
    val feelLikeTemperature: Double,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Double,
    val weatherCode: Int,
    val rain: Double,
    val uvIndex: Double,
    val timeZone: String
    )

