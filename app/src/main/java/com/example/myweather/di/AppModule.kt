package com.example.myweather.di

import org.koin.dsl.module

val AppModules = module {
    includes(
        remoteModule,
        domainModule,
        viewModelModule,
    )
}