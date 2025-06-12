package com.example.myweather.ui.homescreen.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableFloatState
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.entity.CurrentWeatherModel
import com.example.myweather.domain.model.entity.DailyWeatherModel
import com.example.myweather.ui.mapper.toDrawableRes
import com.example.myweather.ui.mapper.toLocalizedStringRes
import com.example.myweather.ui.theme.Urbanest
import com.example.myweather.ui.theme.lightGrayOnSecondary
import com.example.myweather.ui.theme.lightSkyBlue

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun CurrentWeatherInfo(
    currentWeather: CurrentWeatherModel,
    dailyWeather: DailyWeatherModel,
    offset: MutableState<Float>,
) {
    val progress = remember { derivedStateOf { offset.value } }.value

    Box(
        modifier = Modifier
            .height(367.dp - (progress * 160).dp)
            .fillMaxWidth()
    ) {
        Image(
            painter = painterResource(
                currentWeather.weatherCondition.toDrawableRes(isDay = currentWeather.isDay)
            ),
            contentDescription = null,
            modifier = Modifier
                .size(width = 200.dp, height = 220.dp)
                .align(Alignment.TopCenter)
                .graphicsLayer {
                    translationX = -progress * 280
                    scaleX = 1f - (progress * 60 / 300f)
                    scaleY = scaleX
                }
        )
        Column(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .graphicsLayer {
                    translationY = -progress * 70
                    translationX = progress * 250
                },
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                "${currentWeather.temperature}°C",
                color = MaterialTheme.colorScheme.primary,
                fontSize = 64.sp,
                fontFamily = Urbanest,
                fontWeight = FontWeight.SemiBold,
            )
            Text(
                stringResource(currentWeather.weatherCondition.toLocalizedStringRes()),
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanest,
                color = MaterialTheme.colorScheme.secondary
            )
            Row(
                modifier = Modifier
                    .padding(top = 12.dp)
                    .clip(shape = RoundedCornerShape(100.dp))
                    .background(lightGrayOnSecondary)
                    .padding(horizontal = 24.dp, vertical = 8.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    painter = painterResource(R.drawable.arrow_up),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,

                    )
                Text(
                    text = "${dailyWeather.temperatureMax.toInt()}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Urbanest,
                    color = MaterialTheme.colorScheme.onSecondary
                )
                VerticalDivider(
                    color = MaterialTheme.colorScheme.onSecondary,
                    modifier = Modifier
                        .padding(horizontal = 8.dp)
                        .width(2.dp)
                        .height(14.dp)
                )
                Icon(
                    painter = painterResource(R.drawable.arrow_down),
                    contentDescription = null,
                    tint = MaterialTheme.colorScheme.onSecondary,

                    )
                Text(
                    text = "${dailyWeather.temperatureMin.toInt()}°C",
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Medium,
                    fontFamily = Urbanest,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }


}

@Composable
fun WeatherDetailsItem(
    modifier: Modifier = Modifier,
    weatherIcon: Int,
    titleInfo: String,
    itemLabel: String,
) {
    Card(
        modifier = modifier
            .border(
                width = 1.dp,
                color = MaterialTheme.colorScheme.onSurface,
                shape = RoundedCornerShape(size = 24.dp)
            )
            .clip(shape = RoundedCornerShape(24)),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground),
    ) {
        Column(
            modifier = Modifier
                .align(alignment = Alignment.CenterHorizontally)
                .padding(vertical = 16.dp, horizontal = 8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Icon(
                painter = painterResource(weatherIcon),
                contentDescription = null,
                tint = lightSkyBlue
            )
            Text(
                text = titleInfo,
                fontSize = 20.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanest,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier.padding(top = 8.dp),
                letterSpacing = 0.25.sp,
                textAlign = TextAlign.Center
            )
            Text(
                text = itemLabel,
                fontSize = 14.sp,
                fontWeight = FontWeight.Normal,
                fontFamily = Urbanest,
                color = MaterialTheme.colorScheme.secondary,
                textAlign = TextAlign.Center
            )
        }
    }
}

