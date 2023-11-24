package com.hellcorp.restquest.data.hotel.dto

import com.google.gson.annotations.SerializedName

data class HotelDescriptionDto(
    @SerializedName("description") val description: String?,
    @SerializedName("peculiarities") val peculiarities: List<String>?
)
