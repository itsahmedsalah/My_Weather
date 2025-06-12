package com.example.myweather.domain.repository

import com.example.myweather.domain.model.entity.Location

interface LocationRepository {
    suspend fun getCurrentLocation(): Location
}