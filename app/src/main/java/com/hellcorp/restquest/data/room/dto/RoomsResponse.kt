package com.hellcorp.restquest.data.room.dto

import com.google.gson.annotations.SerializedName

data class RoomsResponse(
    @SerializedName("rooms") val rooms: List<RoomDto>
)
