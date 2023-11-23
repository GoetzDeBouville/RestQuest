package com.hellcorp.restquest.domain.hotel.network

import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow

interface HotelRepository {
    suspend fun getHotelInfo() : Flow<Resource<Hotel>>
}