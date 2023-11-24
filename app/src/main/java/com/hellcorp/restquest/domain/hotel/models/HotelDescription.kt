package com.hellcorp.restquest.domain.hotel.models

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HotelDescription(
    val description: String?,
    val peculiarities: List<String>?
) : Parcelable
