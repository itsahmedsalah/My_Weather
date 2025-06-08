package com.example.myweather.domain.model.entity

data class HourlyWeatherModel(
    val isDay: List<Int>,
    val hourTemperature: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>,
)
