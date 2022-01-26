package com.iagora.wingman.app.auth.presentation.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentSplashScreenBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.util.asString
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("CustomSplashScreen")
class SplashActivity :
    BaseFragment<FragmentSplashScreenBinding>(
        R.layout.fragment_splash_screen,
        { FragmentSplashScreenBinding.bind(it) }) {

    private val viewModel: SplashViewModel by viewModel()

    override fun setView() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (val event = viewModel.loadLogin()) {
                is OnBoardingPage.LoginPage -> {
                    findNavController().navigate(R.id.moveToLogin)
                }
                is OnBoardingPage.MainPage -> {
//                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is OnBoardingPage.Failed -> {
                    Snackbar.make(
                        binding.root,
                        event.showError.uiText.asString(requireContext()),
                        Snackbar.LENGTH_SHORT
                    ).apply { customPrimaryColor(requireContext()) }.show()
                }
            }
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 100L
    }
}