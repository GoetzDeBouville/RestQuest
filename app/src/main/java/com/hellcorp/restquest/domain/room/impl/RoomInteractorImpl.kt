package com.hellcorp.restquest.domain.room.impl

import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import com.hellcorp.restquest.domain.room.Room
import com.hellcorp.restquest.domain.room.network.RoomInteractor
import com.hellcorp.restquest.domain.room.network.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class RoomInteractorImpl(private val repository: RoomRepository) : RoomInteractor {
    override suspend fun getRoomList(): Flow<Pair<List<Room>?, LoadingStatus?>> {
        return repository.getRoomList().map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.errorType)
            }
        }
    }
}
