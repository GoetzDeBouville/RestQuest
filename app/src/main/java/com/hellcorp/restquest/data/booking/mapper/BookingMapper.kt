package com.hellcorp.restquest.data.booking.mapper

import com.hellcorp.restquest.data.booking.dto.BookingDto
import com.hellcorp.restquest.domain.booking.models.Booking

object BookingMapper {
    fun map(dto: BookingDto) = Booking(
        arrivalCountry = dto.arrivalCountry,
        departure = dto.departure,
        fuelCharge = dto.fuelCharge,
        hotelRating = dto.hotelRating,
        hotelAddress = dto.hotelAddress,
        hotelName = dto.hotelName,
        id = dto.id,
        numberOfNights = dto.numberOfNights,
        nutrition = dto.nutrition,
        ratingName = dto.ratingName,
        room = dto.room,
        serviceCharge = dto.serviceCharge,
        tourDateStart = dto.tourDateStart,
        tourDateStop = dto.tourDateStop,
        tourPrice = dto.tourPrice
    )
}