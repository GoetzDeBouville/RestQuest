package com.hellcorp.restquest.domain.room

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Room(
    val id: Int,
    val imageList: List<String>,
    val name: String,
    val peculiarities: List<String>,
    val price: Int,
    val priceConditions: String
) : Parcelable