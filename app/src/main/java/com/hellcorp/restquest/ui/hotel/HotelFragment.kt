package com.hellcorp.restquest.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import com.hellcorp.restquest.databinding.FragmentHotelBinding
import com.hellcorp.restquest.utils.BindingFragment

class HotelFragment : BindingFragment<FragmentHotelBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHotelBinding {
        return FragmentHotelBinding.inflate(inflater, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

}