package com.example.myweather.ui.homescreen.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.entity.HourlyWeatherModel
import com.example.myweather.ui.mapper.toDrawableRes
import com.example.myweather.ui.theme.Urbanest
import com.example.myweather.ui.theme.lightGrayOnSecondary

@Composable
fun HourlyForeCastDetails(
    modifier: Modifier = Modifier,
    hourlyWeatherModel: List<HourlyWeatherModel>?,

    ) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Today",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Urbanest,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(vertical = 24.dp)
        )
        LazyRow {
            items(hourlyWeatherModel?.size!!) { index ->
                ForeCastHourlyCard(hourlyWeatherModel = hourlyWeatherModel[index])
            }
        }
    }
}

@Composable
fun ForeCastHourlyCard(
    modifier: Modifier = Modifier,
    hourlyWeatherModel: HourlyWeatherModel,
) {
    Box {
        Card(
            modifier = Modifier
                .size(height = 120.dp, width = 88.dp)
                .padding(end = 12.dp)
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .clip(shape = RoundedCornerShape(size = 20.dp)),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(bottom = 16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Bottom
            ) {
                Text(
                    "${hourlyWeatherModel.hourTemperature.toInt()}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Urbanest,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier,
                    textAlign = TextAlign.End
                )
                Text(
                    hourlyWeatherModel.time.toString().substringAfter('T'),
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Urbanest,
                    color = MaterialTheme.colorScheme.secondary,
                    modifier = Modifier,
                    textAlign = TextAlign.End
                )
            }
        }
        Image(
            painter = painterResource(
                hourlyWeatherModel.weatherCondition.toDrawableRes(
                    hourlyWeatherModel.isDay
                )
            ),
            contentDescription = null,
            modifier = Modifier
                .size(height = 58.dp, width = 64.dp)
                .align(Alignment.TopCenter)
                .offset(
                    y = (-16).dp,
                    x = (-6).dp
                ),
        )
    }
}
