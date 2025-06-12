package com.example.myweather.data.mapper

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myweather.data.model.DailyWeatherDTO
import com.example.myweather.domain.model.entity.DailyWeatherModel
import java.time.LocalDate

fun DailyWeatherDTO.toDailyWeatherModels(): List<DailyWeatherModel> {

    if (this.daily.time.size != this.daily.temperature_2m_max.size ||
        this.daily.time.size != this.daily.temperature_2m_min.size ||
        this.daily.time.size != this.daily.uv_index_max.size ||
        this.daily.time.size != this.daily.weather_code.size
    ) {
        throw IllegalArgumentException("Daily weather data lists have inconsistent sizes.")
    }

    val dailyWeatherModels = mutableListOf<DailyWeatherModel>()
    for (i in this.daily.time.indices) {
        val model = DailyWeatherModel(
            temperatureMax = this.daily.temperature_2m_max[i],
            temperatureMin = this.daily.temperature_2m_min[i],
            time = LocalDate.parse(this.daily.time[i]),
            weatherCode = this.daily.weather_code[i],
            uvIndexMax = this.daily.uv_index_max[i],
            weatherCondition = mapWeatherCodeToCondition(daily.weather_code[i])
        )
        dailyWeatherModels.add(model)
    }
    return dailyWeatherModels
}