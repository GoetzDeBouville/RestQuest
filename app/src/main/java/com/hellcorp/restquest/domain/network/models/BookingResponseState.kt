package com.hellcorp.restquest.domain.network.models

import com.hellcorp.restquest.domain.booking.models.Booking

sealed interface BookingResponseState {
    data class Content(
        val booking: Booking
    ) : BookingResponseState

    object Loading : BookingResponseState
    object Empty : BookingResponseState
    object ConnectionError : BookingResponseState
}
