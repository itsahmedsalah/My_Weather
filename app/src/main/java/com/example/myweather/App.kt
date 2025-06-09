package com.example.myweather

import android.app.Application
import com.example.myweather.di.AppModules
import org.koin.core.context.GlobalContext.startKoin

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(AppModules)
        }

    }
}