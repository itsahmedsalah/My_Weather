package com.example.myweather.ui.homescreen.composables


import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.domain.model.entity.DailyWeatherModel
import com.example.myweather.ui.mapper.toDrawableRes
import com.example.myweather.ui.theme.Urbanest
import com.example.myweather.ui.theme.lightGrayOnSecondary
import com.example.myweather.utils.toSentenceCase


@Composable
fun DailyForeCastCard(
    modifier: Modifier = Modifier,
    dailyWeatherModel: List<DailyWeatherModel>,
) {
    Column(modifier = modifier.fillMaxWidth()) {
        Text(
            "Next 7 days",
            fontSize = 20.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = Urbanest,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(top = 24.dp, bottom = 12.dp)
        )
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colorScheme.onSurface,
                    shape = RoundedCornerShape(size = 20.dp)
                )
                .clip(shape = RoundedCornerShape(24.dp)),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.onBackground)
        ) {
            dailyWeatherModel.forEachIndexed { index, dailyForecast ->
                DailyForecastItem(dailyForecast = dailyForecast)
                if (index < dailyWeatherModel.lastIndex) {
                    HorizontalDivider(
                        modifier = Modifier.height(1.dp),
                        color = MaterialTheme.colorScheme.onSurface
                    )

                }
            }
        }
    }
}

@Composable
fun DailyForecastItem(modifier: Modifier = Modifier, dailyForecast: DailyWeatherModel) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 14.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            dailyForecast.time.dayOfWeek.toString().toSentenceCase(),
            fontSize = 16.sp,
            fontWeight = FontWeight.Normal,
            fontFamily = Urbanest,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier.weight(1F)
        )
        Image(
            painter = painterResource(dailyForecast.weatherCondition.toDrawableRes()),
            contentDescription = null,
            modifier = Modifier
                .size(height = 32.dp, width = 32.dp)
                .weight(1F)
        )
        Row(
            modifier = Modifier.weight(1F),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(R.drawable.arrow_up),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,

                )
            Text(
                text = "${dailyForecast.temperatureMax.toInt()}°C",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanest,
                color = MaterialTheme.colorScheme.onSecondary
            )
            VerticalDivider(
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
                    .padding(horizontal = 4.dp)
                    .width(2.dp)
                    .height(14.dp)
            )
            Icon(
                painter = painterResource(R.drawable.arrow_down),
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onSecondary,

                )
            Text(
                text = "${dailyForecast.temperatureMin.toInt()}°C",
                fontSize = 16.sp,
                fontWeight = FontWeight.Medium,
                fontFamily = Urbanest,
                color = MaterialTheme.colorScheme.onSecondary,
                modifier = Modifier
            )
        }
    }
}