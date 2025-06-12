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


    private val _dailyWeather = MutableStateFlow<List<DailyWeatherModel>>(emptyList())
    val dailyWeather = _dailyWeather.asStateFlow()

    private val _hourlyWeather = MutableStateFlow<List<HourlyWeatherModel>>(emptyList())
    val hourlyWeather = _hourlyWeather.asStateFlow()

    private val _loading = MutableStateFlow(true)
    val loading = _loading.asStateFlow()

    fun onPermissionGranted() {
        viewModelScope.launch {
            _loading.value = true
            getDailyWeather()
            getHourlyWeather()
            getCurrentWeather()
            _loading.value = false
        }
    }

    suspend fun getCurrentWeather() {

        _currentWeather.update { getWeatherUseCase.getLocationWeather() }

    }

    suspend fun getDailyWeather() {
        _dailyWeather.update {
            getWeatherUseCase.getDailyWeather()
        }
    }

    suspend fun getHourlyWeather() {
        _hourlyWeather.update { getWeatherUseCase.getHourlyWeather() }
    }

}