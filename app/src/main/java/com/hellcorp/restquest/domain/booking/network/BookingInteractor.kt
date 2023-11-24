package com.hellcorp.restquest.domain.booking.network

import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import kotlinx.coroutines.flow.Flow

interface BookingInteractor {
    suspend fun getBookingInfo(): Flow<Pair<Booking?, LoadingStatus?>>
}