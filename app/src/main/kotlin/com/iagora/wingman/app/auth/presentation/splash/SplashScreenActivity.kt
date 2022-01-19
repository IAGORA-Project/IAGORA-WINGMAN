package com.iagora.wingman.app.auth.presentation.splash

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.databinding.ActivitySplashScreenBinding
import com.iagora.wingman.app.auth.presentation.login.LoginActivity
import com.iagora.wingman.app.main_menu.presentation.MainActivity

import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.core.presentation.util.Util.customPrimaryColor
import org.koin.androidx.viewmodel.ext.android.viewModel


@SuppressLint("CustomSplashScreen")
class SplashScreenActivity :
    BaseActivity<ActivitySplashScreenBinding>({ ActivitySplashScreenBinding.inflate(it) }) {

    private val viewModel: SplashViewModel by viewModel()

    override fun setView() {
        Handler(Looper.getMainLooper()).postDelayed({
            when (val event = viewModel.loadLogin()) {
                is OnBoardingPage.LoginPage -> {
                    startActivity(Intent(this, MainActivity::class.java))
                }
                is OnBoardingPage.MainPage -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                }
                is OnBoardingPage.Failed -> {
                    Snackbar.make(
                        binding.root,
                        event.showError.uiText.toString(),
                        Snackbar.LENGTH_SHORT
                    ).apply { customPrimaryColor(this@SplashScreenActivity) }.show()
                }
            }
            finish()
        }, DELAY_TIME)
    }

    companion object {
        private const val DELAY_TIME = 100L
    }
}