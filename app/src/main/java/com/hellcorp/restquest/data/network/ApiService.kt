package com.hellcorp.restquest.data.network

import com.hellcorp.restquest.data.hotel.dto.HotelDto
import com.hellcorp.restquest.data.room.dto.RoomsResponse
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {
    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotelInfo(): Response<HotelDto>

    @GET("v3/8b532701-709e-4194-a41c-1a903af00195")
    suspend fun getRoomList(): Response<RoomsResponse>
}