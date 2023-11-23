package com.hellcorp.restquest.ui

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.NavHostFragment
import com.hellcorp.restquest.R
import com.hellcorp.restquest.databinding.ActivityRootBinding
import com.hellcorp.restquest.utils.Tools

class RootActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRootBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRootBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.container_view) as NavHostFragment
        val navController = navHostFragment.navController

        navController.addOnDestinationChangedListener { _, destination, _ ->
            with(binding) {
                when (destination.id) {
                    R.id.hotelFragment -> tvTitle.text = getText(R.string.hotel)
                    R.id.roomFragment -> tvTitle.text = getText(R.string.room)
                    R.id.bookingFragment -> tvTitle.text = getText(R.string.booking)
                    R.id.successFragment -> tvTitle.text = getText(R.string.order_payed)
                }
                ivArrowBack.isVisible = destination.id != R.id.hotelFragment
            }
        }

        binding.ivArrowBack.setOnClickListener {
            navController.popBackStack()
        }

        statusbarManager()
    }


    private fun statusbarManager() {
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = ContextCompat.getColor(this, R.color.main_background)
        if (Tools.isBackgroundColorLight(ContextCompat.getColor(this, R.color.main_background))) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) window.insetsController?.setSystemBarsAppearance(
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS,
                WindowInsetsController.APPEARANCE_LIGHT_STATUS_BARS
            )
            else window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
