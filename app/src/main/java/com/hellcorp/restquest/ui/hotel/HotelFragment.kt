package com.hellcorp.restquest.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentHotelBinding
import com.hellcorp.restquest.domain.network.models.ResponseState
import com.hellcorp.restquest.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel

class HotelFragment : BindingFragment<FragmentHotelBinding>() {
    private val viewModel: HotelViewModel by viewModel()
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelBinding {
        return FragmentHotelBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvToRoomSelection.setOnClickListener {
            if (viewModel.clickDebounce()) {
                findNavController().navigate(R.id.action_hotelFragment_to_roomFragment)
            }
        }
    }

    private fun liveDataObserver() {
        viewModel.state.observe(viewLifecycleOwner) {

        }
    }

    private fun renderState(state: ResponseState) {
        when (state) {
            is ResponseState.Content -> fetchData()
            is ResponseState.Empty -> showError()
            else ->
        }
    }

    private fun showError() = with(binding) {
        nsvData.visibility = View.GONE
        toRoomSelection.visibility = View.GONE
        ivError.visibility = View.VISIBLE
        tvError.visibility = View.VISIBLE
    }

    private fun fetchData() = with(binding){
        nsvData.visibility = View.VISIBLE
        toRoomSelection.visibility = View.VISIBLE
        ivError.visibility = View.GONE
        tvError.visibility = View.GONE
    }
}