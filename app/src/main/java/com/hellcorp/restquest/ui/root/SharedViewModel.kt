package com.hellcorp.restquest.ui.root

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

open class SharedViewModel : ViewModel() {
    private val _title = MutableLiveData<String>()
    val title: LiveData<String> get() = _title

    fun updateTitle(newTitle: String) {
        _title.value = newTitle
        Log.i("SharedViewModelMyLog", "newTitle = $newTitle")
    }
}

