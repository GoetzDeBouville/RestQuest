package com.hellcorp.restquest.domain.booking.impl

import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.booking.network.BookingInteractor
import com.hellcorp.restquest.domain.booking.network.BookingRepository
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class BookingInteractorImpl(private val repository: BookingRepository) : BookingInteractor {
    override suspend fun getBookingInfo(): Flow<Pair<Booking?, LoadingStatus?>> {
        return repository.getBookingInfo().map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.errorType)
            }
        }
    }
}
