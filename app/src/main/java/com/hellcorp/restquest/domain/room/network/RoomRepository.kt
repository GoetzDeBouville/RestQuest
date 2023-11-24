package com.hellcorp.restquest.domain.room.network

import kotlinx.coroutines.flow.Flow
import com.hellcorp.restquest.domain.network.models.Resource
import com.hellcorp.restquest.domain.room.Room

interface RoomRepository {
    suspend fun getRoomList(): Flow<Resource<List<Room>>>
}
