package com.example.myweather.data.repository

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.location.Geocoder
import androidx.annotation.RequiresPermission
import com.example.myweather.domain.model.entity.Location
import com.example.myweather.domain.repository.LocationRepository
import com.google.android.gms.location.CurrentLocationRequest
import com.google.android.gms.location.LocationServices
import com.google.android.gms.location.Priority
import java.util.Locale
import kotlinx.coroutines.tasks.await

@SuppressLint("MissingPermission")
class LocationRepositoryImp(private val context: Context) : LocationRepository {
    private val fusedLocationProviderClient =
        LocationServices.getFusedLocationProviderClient(context)

    override suspend fun getCurrentLocation(): Location {
        val location = getCurrentLocationFromProvider()
        val cityName = getCityNameFromCoordinates(location.latitude, location.longitude)
        return Location(
            latitude = location.latitude,
            longitude = location.longitude,
            cityName = cityName
        )
    }

    private suspend fun getCurrentLocationFromProvider(): android.location.Location {
        val currentLocationRequest = CurrentLocationRequest.Builder()
            .setPriority(Priority.PRIORITY_HIGH_ACCURACY)
            .setDurationMillis(30000)
            .setMaxUpdateAgeMillis(5000)
            .build()

        return fusedLocationProviderClient.getCurrentLocation(
            currentLocationRequest,
            null
        ).await() ?: throw IllegalStateException("No location available")
    }

    private fun getCityNameFromCoordinates(latitude: Double, longitude: Double): String {
        val geocoder = Geocoder(context, Locale.ENGLISH)
        val addresses = geocoder.getFromLocation(latitude, longitude, 1)
        return if (!addresses.isNullOrEmpty()) {
            addresses[0].locality ?: addresses[0].subAdminArea ?: "Unknown city"
        } else "Unknown city"
    }
}