package com.hellcorp.restquest.ui.booking


import android.view.LayoutInflater
import android.view.ViewGroup
import com.hellcorp.restquest.databinding.FragmentBookingBinding
import com.hellcorp.restquest.utils.BindingFragment

class BookingFragment : BindingFragment<FragmentBookingBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookingBinding {
        return FragmentBookingBinding.inflate(inflater, container, false)
    }
}