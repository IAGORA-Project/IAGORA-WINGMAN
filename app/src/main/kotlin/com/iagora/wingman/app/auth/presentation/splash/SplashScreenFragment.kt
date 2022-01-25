package com.iagora.wingman.app.auth.presentation.splash

import android.annotation.SuppressLint
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentSplashScreenBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.util.Util.customPrimaryColor
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("CustomSplashScreen")
class SplashScreenFragment :
    BaseFragment<FragmentSplashScreenBinding>(
        R.layout.fragment_splash_screen,
        { FragmentSplashScreenBinding.bind(it) }) {

    private val viewModel: SplashViewModel by viewModel()

    override fun setView() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (val event = viewModel.loadLogin()) {
                is OnBoardingPage.LoginPage -> {
//                    startActivity(Intent(this, MainActivity::class.java))
                }
                is OnBoardingPage.MainPage -> {
//                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is OnBoardingPage.Failed -> {
                    Snackbar.make(
                        binding.root,
                        event.showError.uiText.toString(),
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