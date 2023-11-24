package com.hellcorp.restquest.di

import com.hellcorp.restquest.ui.booking.BookingViewModel
import com.hellcorp.restquest.ui.hotel.HotelViewModel
import com.hellcorp.restquest.ui.room.RoomViewModel
import com.hellcorp.restquest.ui.success.SuccessViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val viewModelModule = module {
    viewModelOf(::HotelViewModel)
    viewModelOf(::BookingViewModel)
    viewModelOf(::RoomViewModel)
    viewModelOf(::SuccessViewModel)
}
