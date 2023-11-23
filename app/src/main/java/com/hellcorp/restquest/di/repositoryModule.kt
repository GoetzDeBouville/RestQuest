package com.hellcorp.restquest.di

import com.hellcorp.restquest.data.hotel.repository.HotelRepositoryImpl
import com.hellcorp.restquest.domain.hotel.network.HotelRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::HotelRepositoryImpl) { bind<HotelRepository>() }

}