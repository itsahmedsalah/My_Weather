package com.example.myweather.data.mapper

import com.example.myweather.data.model.WeekWeatherDTO
import com.example.myweather.domain.model.entity.WeekWeatherModel

fun WeekWeatherDTO.toWeekWeatherModel(): WeekWeatherModel {
    return WeekWeatherModel(
        temperatureMax = this.daily.temperature_2m_max,
        temperatureMin = this.daily.temperature_2m_min,
        time = this.daily.time,
        weatherCode = this.daily.weather_code,
        uvIndexMax = this.daily.uv_index_max,
    )
}