package com.hellcorp.restquest.ui.booking


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentBookingBinding
import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.network.models.BookingResponseState
import com.hellcorp.restquest.utils.BindingFragment
import com.hellcorp.restquest.utils.Tools
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Locale

class BookingFragment : BindingFragment<FragmentBookingBinding>() {
    private val viewModel: BookingViewModel by viewModel()
    private var hotelName: String = ""

    override fun createBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBookingBinding {
        return FragmentBookingBinding.inflate(inflater, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.cvPay.setOnClickListener {
            if (viewModel.clickDebounce()) {
                findNavController().navigate(R.id.action_bookingFragment_to_successFragment)
            }
        }
        hotelName = arguments?.getString(Tools.HOTEL_NAME_KEY).toString()
        viewModel.getBookingInfo()
        liveDataObserver()

    }

    private fun fetchData(booking: Booking) = with(binding) {
        val numberFormat = NumberFormat.getNumberInstance(Locale.getDefault())
        val ratingText =
            getString(R.string.hotel_rating, booking.hotelRating.toString(), booking.ratingName)
        val period =
            getString(R.string.dates_start_stop, booking.tourDateStart, booking.tourDateStop)
        val nights = getString(
            R.string.nights_num,
            Tools.nightsPlurals(booking.numberOfNights)
        )
        val tourPrice = booking.tourPrice.let { numberFormat.format(it) } ?: "N/A"
        val tourPriceVal = getString(R.string.minimal_price, tourPrice)
        val fuelTax = booking.fuelCharge.let { numberFormat.format(it) } ?: "N/A"
        val fuelTaxVal = getString(R.string.minimal_price, fuelTax)
        val serviceFee = booking.serviceCharge.let { numberFormat.format(it) } ?: "N/A"
        val fserviceFeeVal = getString(R.string.minimal_price, serviceFee)
        val totalPrice = booking.tourPrice + booking.fuelCharge + booking.serviceCharge
        val totalPriceStr = totalPrice.let { numberFormat.format(it) } ?: "N/A"
        val totalPriceVal = getString(R.string.pay_order, totalPriceStr)

        tvRate.text = ratingText
        tvHotelName.text = hotelName
        tvAddress.text = booking.hotelAddress
        tvDepartFromInfo.text = booking.departure
        tvArriveInInfo.text = booking.arrivalCountry
        tvPeriodInfo.text = period
        tvNightsNumberInfo.text = nights
        tvHotelInfo.text = hotelName
        tvRoomInfo.text = booking.room
        tvMealInfo.text = booking.nutrition
        tvTourAmountInfo.text = tourPriceVal
        tvFuelTaxInfo.text = fuelTaxVal
        tvServiceFeeInfo.text = fserviceFeeVal
        tvTotalPrice.text = totalPriceVal
    }

    private fun liveDataObserver() {
        viewModel.state.observe(viewLifecycleOwner) {
            renderState(it)
        }
    }

    private fun renderState(state: BookingResponseState) {
        when (state) {
            is BookingResponseState.Content -> showData(state.booking)
            is BookingResponseState.Empty -> showError()
            BookingResponseState.Loading -> showLoading()
            else -> showError()
        }
    }


    private fun showData(booking: Booking) {
        fetchData(booking)
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
            llPayment.visibility = contentVisibility
            ivError.visibility = errorVisibility
            tvError.visibility = errorVisibility
            progressBar.visibility = progressBarVisibility
        }
}
