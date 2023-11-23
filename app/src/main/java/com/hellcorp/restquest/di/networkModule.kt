package com.hellcorp.restquest.di

import com.hellcorp.restquest.data.hotel.network.HotelApiService
import com.hellcorp.restquest.data.hotel.network.RetrofitNetworkClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl("https://run.mocky.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(HotelApiService::class.java) }
    singleOf(::RetrofitNetworkClient)
}