package com.hellcorp.restquest.data.booking.repository

import com.hellcorp.restquest.data.booking.mapper.BookingMapper
import com.hellcorp.restquest.data.network.RetrofitNetworkClient
import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.booking.network.BookingRepository
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException

class BookingRepositoryImpl(private val networkClient: RetrofitNetworkClient) : BookingRepository {
    override suspend fun getBookingInfo(): Flow<Resource<Booking>> = flow {
        try {
            val response = networkClient.getBookingInfo()

            if (response.isSuccessful) {
                response.body()?.let { bookingResponse ->
                    emit(Resource.Success(BookingMapper.map(bookingResponse)))
                } ?: throw Exception("No data found")
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
