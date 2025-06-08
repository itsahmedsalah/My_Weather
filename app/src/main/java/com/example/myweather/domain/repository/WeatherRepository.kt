package com.example.myweather.domain.repository

import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.model.entity.Weather

interface WeatherRepository {
  suspend  fun getLocationCurrentWeather(location: Location): Weather
   suspend fun getCurrentWeather(): Weather
}