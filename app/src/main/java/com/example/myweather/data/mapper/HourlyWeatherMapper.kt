package com.example.myweather.data.mapper


import com.example.myweather.data.model.HourlyWeatherDTO
import com.example.myweather.domain.model.entity.HourlyWeatherModel
import java.time.LocalDateTime

fun HourlyWeatherDTO.toHourlyWeather(): List<HourlyWeatherModel> {

    val hourlyWeather = mutableListOf<HourlyWeatherModel>()

    for (i in hourly.time.indices) {
        hourlyWeather.add(
            HourlyWeatherModel(
                isDay = hourly.is_day[i] != 0,
                hourTemperature = hourly.temperature_2m[i],
                time = LocalDateTime.parse(hourly.time[i]),
                weatherCode = hourly.weather_code[i],
                weatherCondition = mapWeatherCodeToCondition(hourly.weather_code[i])
            )
        )
    }
    return hourlyWeather
}

