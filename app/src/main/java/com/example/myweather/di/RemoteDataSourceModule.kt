package com.example.myweather.di

import com.example.myweather.data.repository.LocationRepositoryImp
import com.example.myweather.data.repository.WeatherRepositoryImp
import com.example.myweather.domain.repository.LocationRepository
import com.example.myweather.domain.repository.WeatherRepository
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val remoteModule = module {
    single {
        HttpClient(CIO)
    }

    single<WeatherRepository> { WeatherRepositoryImp(get()) }
    single<LocationRepository> { LocationRepositoryImp(get()) }
}
