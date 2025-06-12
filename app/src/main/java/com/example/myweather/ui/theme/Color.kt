package com.example.myweather.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

val Purple80 = Color(0xFFD0BCFF)
val PurpleGrey80 = Color(0xFFCCC2DC)
val Pink80 = Color(0xFFEFB8C8)

val Purple40 = Color(0xFF6650a4)
val PurpleGrey40 = Color(0xFF625b71)
val Pink40 = Color(0xFF7D5260)

val lightSkyBlue = Color(0xFF87CEFA)
val bluePurple = Color(0xFF060414)
val secondaryBluePurple = Color(0xFF0D0C19)
val grayOnSecondary = Color(0x99060414)

val lightGrayOnSecondary = Color(0x14FFFFFF)

val darkThemeBorder = Color(0x14B7B7B7)

val lightThemeBorder = Color(0x33838383)
val onBackGroundDark = Color(0xB3060414)

val locationLight = Color(0xFF323232)

val lightWhiteSecondary = Color(0x99FFFFFF)


val lightBackGround = Brush.verticalGradient(
    colors = listOf(
        lightSkyBlue, Color.White
    )
)
val darkBackGround = Brush.verticalGradient(
    listOf(bluePurple, secondaryBluePurple)
)