package com.hellcorp.restquest.domain.hotel.network

import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import kotlinx.coroutines.flow.Flow

interface HotelInteractor {
    suspend fun getHotelInfo() : Flow<Pair<Hotel?, LoadingStatus?>>
}