package com.hellcorp.restquest.ui.success

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentSuccessBinding
import com.hellcorp.restquest.utils.BindingFragment

class SuccessFragment : BindingFragment<FragmentSuccessBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSuccessBinding {
        return FragmentSuccessBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvToHotel.setOnClickListener {
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().popBackStack()
            findNavController().navigate(R.id.action_global_to_hotelFragment)
        }
    }
}