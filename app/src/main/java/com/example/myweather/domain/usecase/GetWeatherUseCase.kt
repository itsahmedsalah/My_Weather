package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.repository.LocationRepository
import com.example.myweather.domain.repository.WeatherRepository

class GetWeatherUseCase(
    private val weatherRepository: WeatherRepository,
    private val locationRepository: LocationRepository,
) {
    suspend fun getLocationWeather(location: Location) =
        weatherRepository.getLocationCurrentWeather(getCurrentLocation())

    suspend fun getHourlyWeather(location: Location) =
        weatherRepository.getHourlyWeather(getCurrentLocation())

    suspend fun getDailyWeather(location: Location) =
        weatherRepository.getDailyWeather(getCurrentLocation())

    private suspend fun getCurrentLocation() = locationRepository.getCurrentLocation()
}
