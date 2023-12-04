package com.banklannister.weather.models

sealed class BaseModelState<out T> {
    data class Success<T>(val data: T): BaseModelState<T>()
    data class Error(val error: String):BaseModelState<Nothing>()
    object Loading:BaseModelState<Nothing>()
}