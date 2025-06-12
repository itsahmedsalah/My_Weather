package com.example.myweather.domain.model.entity

import com.example.myweather.domain.model.enums.WeatherCondition
import java.time.LocalDate

data class DailyWeatherModel(
    val temperatureMax: Double,
    val temperatureMin: Double,
    val time: LocalDate,
    val weatherCode: Int,
    val uvIndexMax: Double,
    val weatherCondition: WeatherCondition
)
