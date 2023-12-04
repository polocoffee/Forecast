package com.banklannister.weather.repositories

import com.banklannister.weather.models.BaseModelState
import com.banklannister.weather.models.DailyForecasts
import com.banklannister.weather.models.HourlyForecast
import com.banklannister.weather.models.Location

interface WeatherRepo {
    suspend fun searchLocation(query:String):BaseModelState<List<Location>>
    suspend fun getDailyForecasts(locationKey:String):BaseModelState<DailyForecasts>
    suspend fun getHourlyForecasts(locationKey: String):BaseModelState<List<HourlyForecast>>
}