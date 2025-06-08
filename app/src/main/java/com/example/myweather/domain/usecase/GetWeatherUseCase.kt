package com.example.myweather.domain.usecase

import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.repository.WeatherRepository


class GetWeatherUseCase(private val weatherRepository: WeatherRepository) {
     fun getLocationWeather(location: Location) = weatherRepository.getLocationCurrentWeather(location)
     fun getWeather() = weatherRepository.getCurrentWeather()
}
