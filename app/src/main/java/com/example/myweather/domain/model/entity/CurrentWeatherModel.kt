package com.example.myweather.domain.model.entity

import com.example.myweather.domain.model.enums.WeatherCondition

data class CurrentWeatherModel(
    val isDay: Boolean,
    val temperature: Int,
    val time: String,
    val feelLikeTemperature: Double,
    val humidity: Int,
    val pressure: Double,
    val windSpeed: Double,
    val weatherCode: Int,
    val rain: Double,
    val uvIndex: Double,
    val timeZone: String,
    val weatherCondition: WeatherCondition
)

