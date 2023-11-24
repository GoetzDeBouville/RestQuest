package com.hellcorp.restquest.ui.booking

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.booking.network.BookingInteractor
import com.hellcorp.restquest.domain.network.models.BookingResponseState
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookingViewModel(private val interactor: BookingInteractor) : ViewModel() {
    private var isClickAllowed = true
    private val _state = MutableLiveData<BookingResponseState>()
    val state: LiveData<BookingResponseState> get() = _state
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

    fun getBookingInfo() {
        viewModelScope.launch {
            try {
                interactor.getBookingInfo()
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            } catch (e: Exception) {
                Log.e("Coroutine Exception", e.stackTraceToString())
            }
        }
    }

    private fun processResult(booking: Booking?, errorType: LoadingStatus?) {
        when {
            errorType != null -> renderState(BookingResponseState.ConnectionError)
            booking == null -> renderState(BookingResponseState.Empty)
            else -> renderState(BookingResponseState.Content(booking))
        }
    }

    private fun renderState(state: BookingResponseState) {
        _state.postValue(state)
    }
}
