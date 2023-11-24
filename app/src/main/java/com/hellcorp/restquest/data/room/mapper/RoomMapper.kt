package com.hellcorp.restquest.data.room.mapper

import com.hellcorp.restquest.data.room.dto.RoomDto
import com.hellcorp.restquest.domain.room.Room

class RoomMapper {
    fun mapDtoToEntity(dto: RoomDto) = Room(
        id = dto.id,
        imageList = dto.imageList,
        name = dto.name,
        peculiarities = dto.peculiarities,
        price = dto.price,
        priceConditions = dto.priceConditions
    )
}