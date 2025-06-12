package com.example.myweather.domain.model.entity

import com.example.myweather.domain.model.enums.WeatherCondition
import java.time.LocalDateTime

data class HourlyWeatherModel(
    val isDay: Boolean,
    val hourTemperature: Double,
    val time: LocalDateTime,
    val weatherCode: Int,
    val weatherCondition: WeatherCondition
)
