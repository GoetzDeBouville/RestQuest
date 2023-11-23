package com.hellcorp.restquest.data.hotel.mapper

import com.hellcorp.restquest.data.hotel.dto.HotelDto
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.hotel.models.HotelDescription

object HotelMapper {
    fun mapDtoToEntity(dto: HotelDto) = Hotel(
        aboutHotel = dto.aboutTheHotel.let { HotelDescription(it?.description, it?.peculiarities ?: listOf()) },
        address = dto.address,
        id = dto.id,
        imageList = dto.imageUrls,
        minimalPrice = dto.minimalPrice,
        name = dto.name,
        priceForIt = dto.priceForIt,
        rating = dto.rating,
        ratingName = dto.ratingName
    )
}
