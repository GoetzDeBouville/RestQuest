package com.hellcorp.restquest.ui.booking

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class BookingViewModel : ViewModel() {
    private var isClickAllowed = true
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