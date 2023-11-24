package com.hellcorp.restquest.ui.hotel

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentHotelBinding
import com.hellcorp.restquest.domain.hotel.models.Hotel
import com.hellcorp.restquest.domain.network.models.HotelResponseState
import com.hellcorp.restquest.ui.hotel.adapters.ImagePagerAdapter
import com.hellcorp.restquest.ui.hotel.adapters.PeculiaritiesAdapter
import com.hellcorp.restquest.utils.BindingFragment
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale

class HotelFragment : BindingFragment<FragmentHotelBinding>() {
    private val viewModel: HotelViewModel by viewModel()
    override fun createBinding(
        inflater: LayoutInflater, container: ViewGroup?
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
        viewModel.getHotelInfo()
        liveDataObserver()
    }

    private fun fetchData(hotel: Hotel) = with(binding) {
        val ratingText = getString(R.string.hotel_rating, hotel.rating.toString(), hotel.ratingName)

        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        val formattedPrice = hotel.minimalPrice?.let { numberFormat.format(it) } ?: "N/A"

        val minimalPrice = getString(R.string.minimal_price, formattedPrice)
        val imagePagerAdapter = ImagePagerAdapter(hotel.imageList ?: listOf())
        val peculiaritiesAdapter = PeculiaritiesAdapter(hotel.aboutHotel?.peculiarities ?: listOf())

        tvRate.text = ratingText
        tvHotelName.text = hotel.name
        tvAddress.text = hotel.address
        tvPrice.text = minimalPrice
        tvPriceCondition.text = hotel.priceForIt
        tvDescription.text = hotel.aboutHotel?.description

        viewpager.adapter = imagePagerAdapter
        indicator.setViewPager(binding.viewpager)

        recyclerView.apply {
            adapter = peculiaritiesAdapter
            layoutManager = GridLayoutManager(context, 2)
        }
    }

    private fun liveDataObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: HotelResponseState) {
        when (state) {
            is HotelResponseState.Content -> showData(state.hotel)
            is HotelResponseState.Empty -> showError()
            HotelResponseState.Loading -> showLoading()
            else -> showError()
        }
    }

    private fun showData(hotel: Hotel) {
        fetchData(hotel)
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
            nsvData.visibility = contentVisibility
            toRoomSelection.visibility = contentVisibility
            ivError.visibility = errorVisibility
            tvError.visibility = errorVisibility
            progressBar.visibility = progressBarVisibility
        }
}