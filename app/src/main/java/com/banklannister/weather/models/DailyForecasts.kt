package com.banklannister.weather.models

import com.google.gson.annotations.SerializedName

data class DailyForecasts (
    @SerializedName("DailyForecasts")
    val dailyForecasts: List<DailyForecast>
)