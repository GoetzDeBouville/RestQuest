package com.hellcorp.restquest.di

import com.hellcorp.restquest.domain.hotel.impl.HotelInteractorImpl
import com.hellcorp.restquest.domain.hotel.network.HotelInteractor
import com.hellcorp.restquest.domain.room.impl.RoomInteractorImpl
import com.hellcorp.restquest.domain.room.network.RoomInteractor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val interactorModule = module {
    singleOf(::HotelInteractorImpl) { bind<HotelInteractor>() }
    singleOf(::RoomInteractorImpl) { bind<RoomInteractor>() }
}