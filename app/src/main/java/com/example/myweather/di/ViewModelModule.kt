package com.example.myweather.di

import com.example.myweather.ui.homescreen.HomeScreenViewModel
import org.koin.core.module.dsl.viewModel

import org.koin.dsl.module


val viewModelModule = module {
    viewModel { HomeScreenViewModel(get()) }
}
