package com.ssd.iagorawingman.ui.auth.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ssd.iagorawingman.databinding.ActivityLoginBinding
import com.ssd.iagorawingman.databinding.ActivitySplashScreenBinding
import com.ssd.iagorawingman.ui.auth.AuthViewModel
import com.ssd.iagorawingman.ui.auth.login.LoginActivity
import com.ssd.iagorawingman.ui.main_menu.MainActivity
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                }
            }
        })
    }
}