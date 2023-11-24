package com.hellcorp.restquest.domain.hotel.impl

import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.hotel.network.HotelInteractor
import com.hellcorp.restquest.domain.hotel.network.HotelRepository
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class HotelInteractorImpl(private val repository: HotelRepository) : HotelInteractor {
    override suspend fun getHotelInfo(): Flow<Pair<Hotel?, LoadingStatus?>> {
        return repository.getHotelInfo().map { result ->
            when (result) {
                is Resource.Success -> Pair(result.data, null)
                is Resource.Error -> Pair(null, result.errorType)
            }
        }
    }
}
