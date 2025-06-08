package com.example.myweather.domain.model.entity

data class WeekWeatherModel(
    val temperatureMax: List<Double>,
    val temperatureMin: List<Double>,
    val time: List<String>,
    val weatherCode: List<Int>,
    val uvIndexMax: List<Double>,
)
