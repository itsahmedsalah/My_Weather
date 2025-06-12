package com.example.myweather.ui.theme


import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = bluePurple,
    secondary = grayOnSecondary,
    onBackground = Color.White,
    tertiary = locationLight,
    onSurface = lightThemeBorder

)


private val LightColorScheme = lightColorScheme(
    primary = Color.White,
    secondary = lightWhiteSecondary,
    onBackground = onBackGroundDark,
    tertiary = Color.White,
    onSurface = darkThemeBorder
)

@Composable
fun MyWeatherTheme(
    darkTheme: Boolean = false,
    content: @Composable () -> Unit,
) {

    MaterialTheme(
        colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme,
        typography = Typography,
        content = content
    )
}