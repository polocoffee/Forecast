package com.banklannister.weather.network

import com.banklannister.weather.models.DailyForecasts
import com.banklannister.weather.models.HourlyForecast
import com.banklannister.weather.models.Location
import com.banklannister.weather.utils.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface Api {
    @GET("locations/v1/cities/search")
    suspend fun searchLocation(
        @Query("apikey") apiKey: String = API_KEY,
        @Query("q") query: String
    ): Response<List<Location>>

    @GET("forecasts/v1/daily/5day/{location_key}")
    suspend fun getDailyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("metric") metric: Boolean = true
    ): Response<DailyForecasts>

    @GET("forecasts/v1/hourly/12hour/{location_key}")
    suspend fun getHourlyForecasts(
        @Path("location_key") locationKey: String,
        @Query("apikey") apiKey: String = API_KEY,
        @Query("metric") metric: Boolean = true
    ):Response<List<HourlyForecast>>
}