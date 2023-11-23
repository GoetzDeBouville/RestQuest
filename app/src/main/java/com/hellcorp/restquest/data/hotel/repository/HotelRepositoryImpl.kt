package com.hellcorp.restquest.data.hotel.repository

import com.hellcorp.restquest.data.hotel.mapper.HotelMapper
import com.hellcorp.restquest.data.hotel.network.RetrofitNetworkClient
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.hotel.network.HotelRepository
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException


class HotelRepositoryImpl(private val networkClient: RetrofitNetworkClient) : HotelRepository {
    override suspend fun getHotelInfo(): Flow<Resource<Hotel>> = flow {
        try {
            val response = networkClient.getHotelInfo()
            if (response.isSuccessful) {
                response.body()?.let { hotelResponse ->
                    emit(Resource.Success(HotelMapper.mapDtoToEntity(hotelResponse)))
                } ?: throw Exception("No data found")
            } else {
                emit(Resource.Error<Hotel>(LoadingStatus.SERVER_ERROR))
            }
        } catch (e: IOException) {
            emit(Resource.Error<Hotel>(LoadingStatus.NO_INTERNET))
        } catch (e: HttpException) {
            emit(Resource.Error<Hotel>(LoadingStatus.SERVER_ERROR))
        } catch (e: Exception) {
            emit(Resource.Error<Hotel>(LoadingStatus.SERVER_ERROR))
        }
    }
}