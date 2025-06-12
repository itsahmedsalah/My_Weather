package com.example.myweather.ui.homescreen.composables

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myweather.R
import com.example.myweather.ui.homescreen.HomeScreenViewModel
import com.example.myweather.ui.theme.Urbanest

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun LocationView(modifier: Modifier = Modifier, viewModel: HomeScreenViewModel) {
    Row(
        modifier = modifier.fillMaxWidth(),

        horizontalArrangement = Arrangement.Center
    ) {
        Icon(
            painter = painterResource(R.drawable.location),
            contentDescription = "Location",
            tint = MaterialTheme.colorScheme.tertiary
        )
        Text(
            viewModel.currentWeather.value?.timeZone?.substringAfter('/') ?: "",
            fontSize = 16.sp,
            fontFamily = Urbanest,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = 4.dp),
            color = MaterialTheme.colorScheme.tertiary
        )
    }
}