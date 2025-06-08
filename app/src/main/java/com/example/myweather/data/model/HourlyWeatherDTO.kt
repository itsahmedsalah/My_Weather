package com.example.myweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class HourlyWeatherDTO(
    val elevation: Double,
    val generationtime_ms: Double,
    val hourly: Hourly,
    val hourly_units: HourlyUnits,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int,
)

@Serializable
data class Hourly(
    val is_day: List<Int>,
    val temperature_2m: List<Double>,
    val time: List<String>,
    val weather_code: List<Int>,
)

@Serializable
data class HourlyUnits(
    val is_day: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String,
)