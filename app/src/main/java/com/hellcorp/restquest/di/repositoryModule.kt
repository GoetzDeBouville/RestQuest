package com.hellcorp.restquest.di

import com.hellcorp.restquest.data.hotel.repository.HotelRepositoryImpl
import com.hellcorp.restquest.data.room.mapper.RoomMapper
import com.hellcorp.restquest.data.room.repository.RoomRepositoryImpl
import com.hellcorp.restquest.domain.hotel.network.HotelRepository
import com.hellcorp.restquest.domain.room.network.RoomRepository
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.factoryOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module


val repositoryModule = module {
    singleOf(::HotelRepositoryImpl) { bind<HotelRepository>() }
    singleOf(::RoomRepositoryImpl) { bind<RoomRepository>() }

    factoryOf(::RoomMapper)
}