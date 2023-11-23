package com.hellcorp.restquest.data.hotel.network

import com.hellcorp.restquest.data.hotel.dto.HotelDto
import retrofit2.Response
import retrofit2.http.GET

interface HotelApiService {
    @GET("v3/d144777c-a67f-4e35-867a-cacc3b827473")
    suspend fun getHotelInfo(): Response<HotelDto>
}