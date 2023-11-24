package com.hellcorp.restquest.domain.network.models

import com.hellcorp.restquest.domain.hotel.models.Hotel

sealed interface HotelResponseState {
    data class Content(
        val hotel: Hotel
    ) : HotelResponseState

    object Loading : HotelResponseState
    object Empty : HotelResponseState
    object ConnectionError : HotelResponseState
}
