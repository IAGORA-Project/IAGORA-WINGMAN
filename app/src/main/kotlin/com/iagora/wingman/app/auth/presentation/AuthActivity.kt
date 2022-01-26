package com.iagora.wingman.app.auth.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.auth.presentation.splash.OnBoardingPage
import com.iagora.wingman.app.auth.presentation.splash.SplashViewModel
import com.iagora.wingman.app.databinding.AcitivityAuthBinding
import com.iagora.wingman.core.presentation.util.asString
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import org.koin.androidx.viewmodel.ext.android.viewModel

class AuthActivity : AppCompatActivity() {
    private val viewModel: SplashViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = AcitivityAuthBinding.inflate(layoutInflater)
        installSplashScreen().setKeepOnScreenCondition { selectPage(binding) }
        setContentView(binding.root)
    }

    private fun selectPage(binding: AcitivityAuthBinding) =
        when (val event = viewModel.performOnBoardPage()) {
            is OnBoardingPage.LoginPage -> false
            is OnBoardingPage.MainPage -> {
//                    startActivity(Intent(this, LoginActivity::class.java))
                false
            }
            is OnBoardingPage.Failed -> {
                Snackbar.make(
                    binding.root,
                    event.showError.uiText.asString(this),
                    Snackbar.LENGTH_SHORT
                ).apply { customPrimaryColor(this@AuthActivity) }.show()
                true
            }
        }
}