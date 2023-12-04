package com.banklannister.weather

import android.app.Application
import com.banklannister.weather.network.Api
import com.banklannister.weather.network.HeaderInterceptor
import com.banklannister.weather.repositories.WeatherRepo
import com.banklannister.weather.repositories.WeatherRepoImpl
import com.banklannister.weather.utils.BASE_URL
import okhttp3.OkHttpClient
import org.koin.core.context.startKoin
import org.koin.dsl.bind
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(module {
                single {
                    val client = OkHttpClient.Builder()
                        .addInterceptor(HeaderInterceptor())
                        .build()
                    Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .addConverterFactory(GsonConverterFactory.create())
                        .client(client)
                        .build()
                }
                single {
                    val retrofit: Retrofit = get()
                    retrofit.create(Api::class.java)
                }
                single {
                    val api: Api = get()
                    WeatherRepoImpl(api)
                } bind (WeatherRepo::class)
            })
        }
    }
}