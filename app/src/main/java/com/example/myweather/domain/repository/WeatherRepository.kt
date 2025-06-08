package com.example.myweather.domain.repository

import com.example.myweather.domain.model.entity.HourlyWeatherModel
import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.model.entity.CurrentWeatherModel
import com.example.myweather.domain.model.entity.WeekWeatherModel

interface WeatherRepository {
    suspend fun getLocationCurrentWeather(location: Location): CurrentWeatherModel
    suspend fun getHourlyWeather(location: Location): HourlyWeatherModel
    suspend fun getWeekWeather(location: Location): WeekWeatherModel
}