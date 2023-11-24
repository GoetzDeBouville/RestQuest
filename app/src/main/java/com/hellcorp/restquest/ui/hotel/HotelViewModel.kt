package com.hellcorp.restquest.ui.hotel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.hotel.network.HotelInteractor
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.HotelResponseState
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HotelViewModel(private val interactor: HotelInteractor) : ViewModel() {
    private var isClickAllowed = true
    private val _state = MutableLiveData<HotelResponseState>()
    val state: LiveData<HotelResponseState> get() = _state

    fun clickDebounce(): Boolean {
        val current = isClickAllowed
        if (isClickAllowed) {
            isClickAllowed = false
            viewModelScope.launch {
                delay(Tools.CLICK_DEBOUNCE_DELAY_500_MS)
                isClickAllowed = true
            }
        }
        return current
    }

    fun getHotelInfo() {
        renderState(HotelResponseState.Loading)
        viewModelScope.launch {
            try {
                interactor.getHotelInfo()
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            } catch (e: Exception) {
                Log.e("Coroutine Exception", e.stackTraceToString())
            }
        }
    }

    private fun processResult(hotel: Hotel?, errorType: LoadingStatus?) {
        when {
            errorType != null -> renderState(HotelResponseState.ConnectionError)
            hotel == null -> renderState(HotelResponseState.Empty)
            else -> renderState(HotelResponseState.Content(hotel))
        }
    }

    private fun renderState(state: HotelResponseState) {
        _state.postValue(state)
    }
}
