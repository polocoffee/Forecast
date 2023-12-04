package com.banklannister.weather.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.banklannister.weather.models.BaseModelState
import com.banklannister.weather.models.Location
import com.banklannister.weather.repositories.WeatherRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HomeViewModel : ViewModel(), KoinComponent {
    val repo: WeatherRepo by inject()

    private val _locations: MutableStateFlow<BaseModelState<List<Location>>?> =
        MutableStateFlow(null)
    val locations = _locations.asStateFlow()


    fun searchLocation(query: String) {
        viewModelScope.launch {
            _locations.update { BaseModelState.Loading }
            repo.searchLocation(query).also { data ->
                _locations.update { data }

            }
        }
    }
}