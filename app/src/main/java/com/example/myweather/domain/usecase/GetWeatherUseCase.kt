package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.repository.WeatherRepository


class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
    suspend fun getLocationWeather(location: Location) =
        weatherRepository.getLocationCurrentWeather(location)

    suspend fun getHourlyWeather(location: Location) = weatherRepository.getHourlyWeather(location)

    suspend fun getDailyWeather(location: Location) = weatherRepository.getDailyWeather(location)
}
