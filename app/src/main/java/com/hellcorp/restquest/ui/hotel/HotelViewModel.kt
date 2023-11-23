package com.hellcorp.restquest.ui.hotel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.domain.hotel.network.HotelInteractor
import com.hellcorp.restquest.domain.network.models.ResponseState
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HotelViewModel(private val interactor: HotelInteractor) : ViewModel() {
    private var isClickAllowed = true
    private val _state = MutableLiveData<ResponseState>()
    val state: LiveData<ResponseState> get() = _state

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
}
