package org.example.data.mapper

import com.example.myweather.data.model.IpInfoDTO
import com.example.myweather.domain.model.entity.Location


fun IpInfoDTO.toLocation() = Location(latitude = this.latitude?:0.0, longitude = this.longitude?:0.0)
