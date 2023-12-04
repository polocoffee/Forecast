package com.banklannister.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.weather.models.BaseModelState
import com.banklannister.weather.models.DailyForecasts
import com.banklannister.weather.models.HourlyForecast
import com.banklannister.weather.repositories.WeatherRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject


class WeatherViewModel : ViewModel(), KoinComponent {
    private val repo: WeatherRepo by inject()

    private val _hourlyForecast: MutableStateFlow<BaseModelState<List<HourlyForecast>>> =
        MutableStateFlow(BaseModelState.Loading)
    val hourlyForecast = _hourlyForecast.asStateFlow()

    private val _dailyForecast: MutableStateFlow<BaseModelState<DailyForecasts>> =
        MutableStateFlow(BaseModelState.Loading)
    val dailyForecasts = _dailyForecast.asStateFlow()

    fun getHourlyForecast(locationKey: String) {
        viewModelScope.launch {
            repo.getHourlyForecasts(locationKey).also { data ->
                _hourlyForecast.update { data }
            }
        }
    }

    fun getDailyForecast(locationKey: String) {
        viewModelScope.launch {
            repo.getDailyForecasts(locationKey).also { data ->
                _dailyForecast.update { data }
            }
        }
    }

}