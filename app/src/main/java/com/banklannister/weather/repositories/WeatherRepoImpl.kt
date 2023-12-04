package com.banklannister.weather.repositories

import com.banklannister.weather.models.BaseModelState
import com.banklannister.weather.models.DailyForecasts
import com.banklannister.weather.models.HourlyForecast
import com.banklannister.weather.models.Location
import com.banklannister.weather.network.Api
import retrofit2.Response

class WeatherRepoImpl(private val api: Api) : WeatherRepo {

    override suspend fun searchLocation(query: String): BaseModelState<List<Location>> {
        return request {
            api.searchLocation(query = query)
        }
    }

    override suspend fun getDailyForecasts(locationKey: String): BaseModelState<DailyForecasts> {
        return request {
            api.getDailyForecasts(locationKey = locationKey)
        }
    }

    override suspend fun getHourlyForecasts(locationKey: String): BaseModelState<List<HourlyForecast>> {
        return request {
            api.getHourlyForecasts(locationKey = locationKey)
        }
    }
}
suspend fun <T> request(request: suspend () -> Response<T>): BaseModelState<T> {
    try{
        request().also {
            return if (it.isSuccessful){
                BaseModelState.Success(it.body()!!)
            }else {
                BaseModelState.Error(it.errorBody()?.string().toString())
            }
        }
    } catch (e: Exception){
        return BaseModelState.Error(e.message.toString())
    }
}