package com.hellcorp.restquest.data.hotel.network

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import com.hellcorp.restquest.data.hotel.dto.HotelDto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.Response


class RetrofitNetworkClient(
    private val hotelApiService: HotelApiService,
    private val context: Context
) {

    suspend fun getHotelInfo(): Response<HotelDto> {
        if (!isConnected()) {
            return Response.error(400, okhttp3.ResponseBody.create(null, "No Internet Connection"))
        }

        return withContext(Dispatchers.IO) {
            try {
                hotelApiService.getHotelInfo()
            } catch (e: Throwable) {
                Response.error(
                    500,
                    okhttp3.ResponseBody.create(null, "Error occurred: ${e.message}")
                )
            }
        }
    }

    @SuppressLint("NewApi")
    private fun isConnected(): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val capabilities =
            connectivityManager.getNetworkCapabilities(connectivityManager.activeNetwork)
        return capabilities != null && (
                capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) ||
                        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)
                )
    }
}
