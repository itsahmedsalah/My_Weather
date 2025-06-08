package com.example.myweather.domain.model.entity


data class Weather(
    val temperature: Double,
    val temperatureUnit: String,
    val weatherCondition: WeatherCondition,
)

