package com.example.myweather.di

import com.example.myweather.data.repository.WeatherRepositoryImp
import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import org.koin.dsl.module

val remoteModule = module {
    single {
        HttpClient(CIO)
    }

    single<WeatherRepositoryImp> { WeatherRepositoryImp(get()) }
}