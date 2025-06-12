package com.example.myweather.data.repository

import android.os.Build
import androidx.annotation.RequiresApi
import com.example.myweather.data.mapper.toCurrentWeatherModel
import com.example.myweather.data.mapper.toHourlyWeather
import com.example.myweather.data.mapper.toDailyWeatherModels
import com.example.myweather.data.model.CurrentWeatherDTO
import com.example.myweather.data.model.HourlyWeatherDTO
import com.example.myweather.data.model.DailyWeatherDTO
import com.example.myweather.data.utils.tryToExecute
import com.example.myweather.domain.model.entity.HourlyWeatherModel
import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.model.entity.CurrentWeatherModel
import com.example.myweather.domain.model.entity.DailyWeatherModel
import com.example.myweather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json


class WeatherRepositoryImp(private val httpClient: HttpClient) : WeatherRepository {
    override suspend fun getLocationCurrentWeather(location: Location): CurrentWeatherModel {
        return tryToExecute(
            function = {

                val response =
                    httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=30.04&longitude=31.23&current=temperature_2m,relative_humidity_2m,apparent_temperature,is_day,rain,weather_code,surface_pressure,wind_speed_10m&timezone=auto")
                        .bodyAsText()
                val weather = Json.decodeFromString<CurrentWeatherDTO>(response)
                weather
            },
            onSuccess = { weather -> weather },
            onFailure = { exception: Exception -> throw exception },

            ).toCurrentWeatherModel()
    }


    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getHourlyWeather(location: Location): List<HourlyWeatherModel> {
        return tryToExecute(
            function = {

                val response =
                    httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=30.04&longitude=31.23&hourly=weather_code,temperature_2m,is_day&forecast_days=1")
                        .bodyAsText()
                val weather = Json.decodeFromString<HourlyWeatherDTO>(response)
                weather
            },
            onSuccess = { weather -> weather },
            onFailure = { exception: Exception -> throw exception },

            ).toHourlyWeather()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override suspend fun getDailyWeather(location: Location): List<DailyWeatherModel> {
        return tryToExecute(
            function = {
                val response =
                    httpClient.get("https://api.open-meteo.com/v1/forecast?latitude=30.04&longitude=31.23&daily=weather_code,temperature_2m_max,temperature_2m_min,uv_index_max")
                        .bodyAsText()
                val weather = Json.decodeFromString<DailyWeatherDTO>(response)
                weather
            },
            onSuccess = { weather -> weather },
            onFailure = { exception: Exception -> throw exception },

            ).toDailyWeatherModels()
    }


}