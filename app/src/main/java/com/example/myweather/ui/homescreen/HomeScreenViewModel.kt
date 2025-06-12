package com.example.myweather.ui.homescreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myweather.domain.model.entity.CurrentWeatherModel
import com.example.myweather.domain.model.entity.DailyWeatherModel
import com.example.myweather.domain.model.entity.HourlyWeatherModel
import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.usecase.GetWeatherUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class HomeScreenViewModel(private val getWeatherUseCase: GetWeatherUseCase) : ViewModel() {

    private val _currentWeather = MutableStateFlow<CurrentWeatherModel?>(null)
    val currentWeather = _currentWeather.asStateFlow()

    init {
        viewModelScope.launch {
            getDailyWeather()
            getHourlyWeather()
            getCurrentWeather()
        }
    }

    private val _dailyWeather = MutableStateFlow<List<DailyWeatherModel>>(emptyList())
    val dailyWeather = _dailyWeather.asStateFlow()

    private val _hourlyWeather = MutableStateFlow<List<HourlyWeatherModel>>(emptyList())
    val hourlyWeather = _hourlyWeather.asStateFlow()

    suspend fun getCurrentWeather() {
        _currentWeather.update { getWeatherUseCase.getLocationWeather(Location(31.23, 30.04)) }
    }

    suspend fun getDailyWeather() {
        _dailyWeather.update {
            getWeatherUseCase.getDailyWeather(Location(31.23, 30.04))
        }
    }

    suspend fun getHourlyWeather() {
        _hourlyWeather.update { getWeatherUseCase.getHourlyWeather(Location(31.23, 30.04)) }
    }

}