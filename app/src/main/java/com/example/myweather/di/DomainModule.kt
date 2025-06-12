package com.example.myweather.di

import com.example.myweather.domain.usecase.GetWeatherUseCase
import org.koin.dsl.module

val domainModule = module {
    single { GetWeatherUseCase(get(),get()) }

}