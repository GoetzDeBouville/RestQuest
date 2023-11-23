package com.hellcorp.restquest.di

import com.hellcorp.restquest.domain.hotel.impl.HotelInteractorImpl
import com.hellcorp.restquest.domain.hotel.network.HotelInteractor
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val interactorModule = module {
    singleOf(::HotelInteractorImpl) { bind<HotelInteractor>() }
}