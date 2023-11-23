package com.hellcorp.restquest.domain.hotel.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hotel(
    val aboutHotel: HotelDescription?,
    val address: String?,
    val id: Int,
    val imageList: List<String>?,
    val minimalPrice: Int?,
    val name: String?,
    val priceForIt: String?,
    val rating: Int?,
    val ratingName: String?
) : Parcelable
