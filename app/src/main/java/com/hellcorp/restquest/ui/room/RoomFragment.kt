package com.hellcorp.restquest.ui.room

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hellcorp.restquest.databinding.FragmentRoomBinding
import com.hellcorp.restquest.utils.BindingFragment

class RoomFragment : BindingFragment<FragmentRoomBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding {
        return FragmentRoomBinding.inflate(inflater, container, false)
    }
}