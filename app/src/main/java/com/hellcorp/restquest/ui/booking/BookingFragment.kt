package com.hellcorp.restquest.ui.booking


import android.app.DatePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.FragmentBookingBinding
import com.hellcorp.restquest.domain.booking.models.Booking
import com.hellcorp.restquest.domain.network.models.BookingResponseState
import com.hellcorp.restquest.utils.BindingFragment
import com.hellcorp.restquest.utils.Tools
import com.redmadrobot.inputmask.MaskedTextChangedListener
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.NumberFormat
import java.util.Calendar
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
        typeListeners()
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

    private fun typeListeners() = with(binding) {
        etEmail.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                if (!Patterns.EMAIL_ADDRESS.matcher(s.toString()).matches()) {
                    emailInput.boxStrokeColor =
                        ContextCompat.getColor(requireContext(), R.color.error_color)
                } else {
                    emailInput.boxStrokeColor =
                        ContextCompat.getColor(requireContext(), R.color.grey_text)
                }
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }
        })

        MaskedTextChangedListener.installOn(
            etNumber,
            "+7 ([000]) [000]-[00]-[00]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                }
            }
        )

        MaskedTextChangedListener.installOn(
            etPassport,
            "[00] [0000000]",
            object : MaskedTextChangedListener.ValueListener {
                override fun onTextChanged(
                    maskFilled: Boolean,
                    extractedValue: String,
                    formattedValue: String,
                    tailPlaceholder: String
                ) {
                }
            })

        etBirthDate.isFocusable = false
        etBirthDate.isClickable = true
        etBirthDate.setOnClickListener { showDatePickerDialog(etBirthDate) }

        etPassportDate.isFocusable = false
        etPassportDate.isClickable = true
        etPassportDate.setOnClickListener { showDatePickerDialog(etPassportDate) }
    }


    private fun showDatePickerDialog(editText: EditText) {
        val calendar = Calendar.getInstance()
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)

        val datePickerDialog = DatePickerDialog(requireContext(), { _, selectedYear, selectedMonth, dayOfMonth ->
            editText.setText(String.format("%02d.%02d.%04d", dayOfMonth, selectedMonth + 1, selectedYear))
        }, year, month, day)

        datePickerDialog.show()
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
