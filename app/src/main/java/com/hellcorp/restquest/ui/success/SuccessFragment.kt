package com.hellcorp.restquest.ui.success

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hellcorp.restquest.databinding.FragmentSuccessBinding
import com.hellcorp.restquest.utils.BindingFragment

class SuccessFragment : BindingFragment<FragmentSuccessBinding>() {
    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSuccessBinding {
        return FragmentSuccessBinding.inflate(inflater, container, false)
    }
}