package com.hellcorp.restquest.domain.room.network

import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import com.hellcorp.restquest.domain.room.Room
import kotlinx.coroutines.flow.Flow

interface RoomInteractor {
    suspend fun getRoomList() : Flow<Pair<List<Room>?, LoadingStatus?>>

}