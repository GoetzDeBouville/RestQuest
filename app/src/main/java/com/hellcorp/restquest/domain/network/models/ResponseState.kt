package com.hellcorp.restquest.domain.network.models

import com.hellcorp.restquest.domain.hotel.models.Hotel

sealed interface ResponseState {
    data class Content(
        val hotel: Hotel
    ) : ResponseState

    object Loading : ResponseState
    object Empty : ResponseState
    object ConnectionError : ResponseState
}
