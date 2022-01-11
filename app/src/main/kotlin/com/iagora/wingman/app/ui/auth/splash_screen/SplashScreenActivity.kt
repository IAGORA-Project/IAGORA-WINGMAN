package com.iagora.wingman.app.ui.auth.splash_screen

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.iagora.wingman.app.databinding.ActivitySplashScreenBinding
import com.iagora.wingman.app.ui.auth.AuthViewModel
import com.iagora.wingman.app.ui.auth.login.LoginActivity
import com.iagora.wingman.app.ui.main_menu.MainActivity
import com.iagora.wingman.helper.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

@SuppressLint("CustomSplashScreen")
class SplashScreenActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashScreenBinding
    private val authViewModel: AuthViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        timeOutSplashScreen()
    }

    private fun timeOutSplashScreen() {
        Handler(Looper.getMainLooper()).postDelayed({
            checkAuth()
        }, 3000)
    }

    private fun checkAuth() {
        authViewModel.vmSplashScreen().observe(this, {
            it.getContentIfNotHandled()?.let {
                when(it.status){
                    Status.SUCCESS -> {
                        println("SUKKKSESSSSS ")
                        startActivity(Intent(this, MainActivity::class.java))
                        finishAffinity()
                    }
                    Status.ERROR -> {
                        startActivity(Intent(this, LoginActivity::class.java))
                        finishAffinity()
                    }
                    else -> {}
                }
            }
        })
    }
}