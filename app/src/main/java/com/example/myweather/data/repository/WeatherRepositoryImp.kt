package com.example.myweather.data.repository

import com.example.myweather.data.model.IpInfoDTO
import com.example.myweather.data.model.WeatherDTO
import com.example.myweather.data.utils.tryToExecute
import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.model.entity.Weather
import com.example.myweather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import org.example.data.mapper.toLocation
import org.example.data.mapper.toWeather


class WeatherRepositoryImp(private val httpClient: HttpClient) : WeatherRepository {
    override suspend fun getLocationCurrentWeather(location: Location): Weather {
        return tryToExecute(
            {
                val url =
                    "$OPEN_METEO_URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true"
                val response = httpClient.get(url)
                val weatherDTO = response.body<WeatherDTO>()
                weatherDTO
            },
            onSuccess = { weather -> weather },
            onFailure = { exception -> throw exception }).toWeather()
    }

    override suspend fun getCurrentWeather(): Weather {
        return tryToExecute(
            {
                val location = getLocation()
                val url =
                    "$OPEN_METEO_URL?latitude=${location.latitude}&longitude=${location.longitude}&current_weather=true"
                val response = httpClient.get(url)
                val weatherDTO = response.body<WeatherDTO>()
                weatherDTO
            },
            onSuccess = { weather -> weather },
            onFailure = { exception -> throw exception }).toWeather()
    }

    private suspend fun getLocation(): Location {
        return tryToExecute(
            {
                val ipResponse = httpClient.get(IPIFY_URL).body<String>()
                val url =
                    "$IPI_API_URL$ipResponse?fields=status,country,regionName,city,lat,lon,timezone"
                val response = httpClient.get(url)
                val ipInfoDTO = response.body<IpInfoDTO>()
                ipInfoDTO
            },
            onFailure = { exception -> throw exception },
            onSuccess = { location -> location }).toLocation()
    }

    companion object {
        private const val OPEN_METEO_URL = "https://api.open-meteo.com/v1/forecast"
        private const val IPIFY_URL = "https://api.ipify.org"
        private const val IPI_API_URL = "http://ip-api.com/json/"
    }
}