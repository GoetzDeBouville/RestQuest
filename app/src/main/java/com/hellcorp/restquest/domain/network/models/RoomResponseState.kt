package com.hellcorp.restquest.domain.network.models

import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.room.Room

sealed interface RoomResponseState {
    data class Content(
        val roomList: List<Room>
    ) : RoomResponseState

    object Loading : RoomResponseState
    object Empty : RoomResponseState
    object ConnectionError : RoomResponseState
}
