package com.example.myweather.data.model

import kotlinx.serialization.Serializable

@Serializable
data class CurrentWeatherDTO(
    val current: Current,
    val current_units: CurrentUnits,
    val elevation: Double,
    val generationtime_ms: Double,
    val latitude: Double,
    val longitude: Double,
    val timezone: String,
    val timezone_abbreviation: String,
    val utc_offset_seconds: Int
)
@Serializable
data class Current(
    val apparent_temperature: Double,
    val interval: Int,
    val is_day: Int,
    val rain: Double,
    val relative_humidity_2m: Int,
    val surface_pressure: Double,
    val temperature_2m: Double,
    val time: String,
    val weather_code: Int,
    val wind_speed_10m: Double
)
@Serializable
data class CurrentUnits(
    val apparent_temperature: String,
    val interval: String,
    val is_day: String,
    val rain: String,
    val relative_humidity_2m: String,
    val surface_pressure: String,
    val temperature_2m: String,
    val time: String,
    val weather_code: String,
    val wind_speed_10m: String
)