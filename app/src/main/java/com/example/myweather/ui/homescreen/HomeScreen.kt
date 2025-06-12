package com.example.myweather.ui.homescreen

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawingPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.unit.dp
import com.example.myweather.R
import com.example.myweather.ui.homescreen.composables.DailyForeCastCard
import com.example.myweather.ui.homescreen.composables.HourlyForeCastDetails
import com.example.myweather.ui.homescreen.composables.LocationView
import com.example.myweather.ui.homescreen.composables.WeatherDetailsItem
import com.example.myweather.ui.homescreen.composables.CurrentWeatherInfo
import com.example.myweather.ui.theme.MyWeatherTheme
import com.example.myweather.ui.theme.darkBackGround
import com.example.myweather.ui.theme.lightBackGround
import kotlinx.coroutines.runBlocking
import org.koin.androidx.viewmodel.ext.android.getViewModel
import kotlin.math.max
import kotlin.math.min

class HomeScreen : ComponentActivity() {
    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val viewModel: HomeScreenViewModel = getViewModel<HomeScreenViewModel>()
        runBlocking {
            viewModel.getCurrentWeather()
            viewModel.getDailyWeather()
            viewModel.getHourlyWeather()
        }
        val isDay = viewModel.currentWeather.value?.isDay ?: true
        setContent {
            MyWeatherTheme(darkTheme = isDay) {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                ) {
                    WeatherHomeScreen(
                        modifier = Modifier, viewModel, isDay
                    )
                }
            }
        }
    }
}

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun WeatherHomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeScreenViewModel,
    isDay: Boolean,
) {
    val offset = remember { mutableFloatStateOf(0f) }
    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            var scrollOffset = 0f

            private fun updateOffset(): Offset {
                val progress = max(0f, min(scrollOffset / 200f, 1f))
                offset.floatValue = progress
                return Offset.Zero
            }

            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                scrollOffset -= available.y
                return updateOffset()
            }

            override fun onPostScroll(
                consumed: Offset,
                available: Offset,
                source: NestedScrollSource,
            ): Offset {
                scrollOffset += available.y
                return updateOffset()
            }
        }
    }
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(
                if (isDay) lightBackGround else darkBackGround
            )
            .padding(horizontal = 12.dp)
            .safeDrawingPadding(),
        horizontalAlignment = Alignment.CenterHorizontally,

        ) {
        LocationView(viewModel = viewModel)
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .nestedScroll(nestedScrollConnection),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            item {
                viewModel.currentWeather.value?.let {
                    CurrentWeatherInfo(
                        it,
                        viewModel.dailyWeather.value[0],
                        offset
                    )
                }
            }
            item {

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 24.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 6.dp, alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {


                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.fast_wind,
                        titleInfo = "${viewModel.currentWeather.value?.windSpeed?.toInt()} KM/h",
                        itemLabel = "Wind",
                    )
                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.humidity,
                        titleInfo = "${viewModel.currentWeather.value?.humidity}%",
                        itemLabel = "Humidity",
                    )
                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.rain,
                        titleInfo = "${viewModel.currentWeather.value?.rain?.toInt()}%",
                        itemLabel = "Rain",
                    )
                }

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    horizontalArrangement = Arrangement.spacedBy(
                        space = 6.dp, alignment = Alignment.CenterHorizontally
                    ),
                    verticalAlignment = Alignment.CenterVertically,
                ) {


                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.uv,
                        titleInfo = "${viewModel.currentWeather.value?.uvIndex?.toInt()} ",
                        itemLabel = "UV Index",
                    )
                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.pressure,
                        titleInfo = "${viewModel.currentWeather.value?.pressure?.toInt()} hPa",
                        itemLabel = "Pressure",
                    )
                    WeatherDetailsItem(
                        modifier = Modifier
                            .weight(1F)
                            .fillMaxWidth()
                            .align(Alignment.CenterVertically),
                        weatherIcon = R.drawable.feels_like,
                        titleInfo = "${viewModel.currentWeather.value?.feelLikeTemperature?.toInt()}°C",
                        itemLabel = "Rain",
                    )
                }
            }
            item {
                HourlyForeCastDetails(
                    hourlyWeatherModel = viewModel.hourlyWeather.value
                )
            }
            item {
                DailyForeCastCard(dailyWeatherModel = viewModel.dailyWeather.value)
            }
        }
    }
}



