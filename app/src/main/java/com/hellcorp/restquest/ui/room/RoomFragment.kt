package com.hellcorp.restquest.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.hellcorp.restquest.databinding.FragmentRoomBinding
import com.hellcorp.restquest.ui.room.adapter.RoomsAdapter
import com.hellcorp.restquest.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : BindingFragment<FragmentRoomBinding>() {
    private val viewModel: RoomViewModel by viewModel()
    private val adapter = RoomsAdapter()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding {
        return FragmentRoomBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}