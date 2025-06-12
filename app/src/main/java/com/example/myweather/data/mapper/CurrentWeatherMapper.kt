package com.example.myweather.data.mapper

import com.example.myweather.data.model.CurrentWeatherDTO
import com.example.myweather.domain.model.entity.CurrentWeatherModel

fun CurrentWeatherDTO.toCurrentWeatherModel(): CurrentWeatherModel {
    return CurrentWeatherModel(
        isDay = this.current.is_day != 0,
        temperature = this.current.temperature_2m.toInt(),
        time = this.current.time,
        feelLikeTemperature = this.current.apparent_temperature,
        humidity = this.current.relative_humidity_2m,
        pressure = this.current.surface_pressure,
        windSpeed = this.current.wind_speed_10m,
        weatherCode = this.current.weather_code,
        rain = this.current.rain,
        uvIndex = this.current.wind_speed_10m,
        timeZone = this.timezone,
        weatherCondition = mapWeatherCodeToCondition(this.current.weather_code)
    )
}


