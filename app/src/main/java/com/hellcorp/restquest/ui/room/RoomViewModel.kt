package com.hellcorp.restquest.ui.room

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hellcorp.restquest.domain.network.models.LoadingStatus
import com.hellcorp.restquest.domain.network.models.RoomResponseState
import com.hellcorp.restquest.domain.room.Room
import com.hellcorp.restquest.domain.room.network.RoomInteractor
import com.hellcorp.restquest.ui.root.SharedViewModel
import com.hellcorp.restquest.utils.Tools
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RoomViewModel(private val interactor: RoomInteractor) : SharedViewModel() {
    private var isClickAllowed = true
    private val _state = MutableLiveData<RoomResponseState>()
    val state: LiveData<RoomResponseState> get() = _state

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

    fun getRoomList() {
        renderState(RoomResponseState.Loading)

        viewModelScope.launch {
            try {
                interactor.getRoomList()
                    .collect { pair ->
                        processResult(pair.first, pair.second)
                    }
            } catch (e: Exception) {
                Log.e("Coroutine Exception", e.stackTraceToString())
            }
        }

    }

    private fun processResult(roomList: List<Room>?, errorType: LoadingStatus?) {
        when {
            errorType != null -> renderState(RoomResponseState.ConnectionError)
            roomList == null -> renderState(RoomResponseState.Empty)
            roomList.isEmpty() -> renderState(RoomResponseState.Empty)
            else -> renderState(RoomResponseState.Content(roomList))
        }
    }

    private fun renderState(state: RoomResponseState) {
        _state.postValue(state)
    }
}