package com.hellcorp.restquest.ui.room

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoomViewModel : ViewModel() {
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