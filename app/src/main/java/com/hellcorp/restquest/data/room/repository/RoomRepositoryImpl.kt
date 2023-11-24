package com.hellcorp.restquest.data.room.repository

import com.hellcorp.restquest.data.network.RetrofitNetworkClient
import com.hellcorp.restquest.data.room.mapper.RoomMapper
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import com.hellcorp.restquest.domain.room.Room
import com.hellcorp.restquest.domain.room.network.RoomRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class RoomRepositoryImpl(private val networkClient: RetrofitNetworkClient) : RoomRepository {
    private val mapper = RoomMapper()
    override suspend fun getRoomList(): Flow<Resource<List<Room>>> = flow {
        try {
            val response = networkClient.getRoomList()
            if (response.isSuccessful) {
                response.body()?.let { roomsResponse ->
                    emit(Resource.Success(roomsResponse.rooms.map {
                        mapper.mapDtoToEntity(it)
                    }))
                }
            } else {
                emit(Resource.Error(LoadingStatus.SERVER_ERROR))
            }
        } catch (e: IOException) {
            emit(Resource.Error(LoadingStatus.NO_INTERNET))
        } catch (e: HttpException) {
            emit(Resource.Error(LoadingStatus.SERVER_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error(LoadingStatus.SERVER_ERROR))
        }
    }
}