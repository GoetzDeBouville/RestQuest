package com.hellcorp.restquest.ui.room

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentRoomBinding
import com.hellcorp.restquest.domain.network.models.RoomResponseState
import com.hellcorp.restquest.domain.room.Room
import com.hellcorp.restquest.ui.room.adapter.RoomsAdapter
import com.hellcorp.restquest.utils.BindingFragment
import com.hellcorp.restquest.utils.Tools
import org.koin.androidx.viewmodel.ext.android.viewModel

class RoomFragment : BindingFragment<FragmentRoomBinding>() {
    private val viewModel: RoomViewModel by viewModel()
    private var hotelName: String = ""

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentRoomBinding {
        return FragmentRoomBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hotelName = arguments?.getString(Tools.HOTEL_NAME_KEY).toString()
        viewModel.updateTitle(hotelName)
        liveDataObserver()

        viewModel.getRoomList()
    }

    private fun liveDataObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: RoomResponseState) {
        when (state) {
            is RoomResponseState.Content -> showData(state.roomList)
            is RoomResponseState.Empty -> showError()
            is RoomResponseState.Loading -> showLoading()
            else -> showError()
        }
    }

    private fun showData(roomList: List<Room>) {
        val adapter = RoomsAdapter(roomList) {
            val bundle = Bundle().apply {
                putString(Tools.HOTEL_NAME_KEY, hotelName)
            }
            findNavController().navigate(R.id.action_roomFragment_to_bookingFragment, bundle)
        }

        binding.rvRooms.adapter = adapter
        binding.rvRooms.layoutManager = LinearLayoutManager(context)
        updateUi(
            contentVisibility = View.VISIBLE,
            errorVisibility = View.GONE,
            progressBarVisibility = View.GONE
        )
    }

    private fun showError() {
        updateUi(
            contentVisibility = View.GONE,
            errorVisibility = View.VISIBLE,
            progressBarVisibility = View.GONE
        )
    }

    private fun showLoading() {
        updateUi(
            contentVisibility = View.GONE,
            errorVisibility = View.GONE,
            progressBarVisibility = View.VISIBLE
        )
    }

    private fun updateUi(contentVisibility: Int, errorVisibility: Int, progressBarVisibility: Int) =
        with(binding) {
            nsvRooms.visibility = contentVisibility
            ivError.visibility = errorVisibility
            tvError.visibility = errorVisibility
            progressBar.visibility = progressBarVisibility
        }
}
