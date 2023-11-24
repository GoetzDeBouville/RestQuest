package com.hellcorp.restquest.domain.booking.network

import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow

interface BookingRepository {
    suspend fun getBookingInfo() : Flow<Resource<Booking>>
}