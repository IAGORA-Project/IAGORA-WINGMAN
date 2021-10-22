package com.ssd.iagorawingman.ui.auth.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssd.iagorawingman.data.source.remote.body.LoginBody
import com.ssd.iagorawingman.databinding.ActivityLoginBinding
import com.ssd.iagorawingman.ui.auth.AuthViewModel
import com.ssd.iagorawingman.ui.main_menu.MainActivity
import com.ssd.iagorawingman.utils.Loader
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Loader.handleLoading(this)
        handleAction()
    }

    private fun handleAction() {
        binding.incButtonMasuk.btnPrimary.setOnClickListener {
            val phone_number =  binding.tilNomorPonsel.editText?.text.toString()
            val password =  binding.tilPassword.editText?.text.toString()
            val body = LoginBody("082239720318", "wingman", "")

            login(body)
        }
    }

    private fun login(loginBody: LoginBody) {
        authViewModel.vmLogin(loginBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        Loader.progressDialog?.show()
                    }
                    Status.SUCCESS -> {
                        Loader.progressDialog?.dismiss()
                        startActivity(Intent(this, MainActivity::class.java))
                        finishAffinity()
                    }
                    Status.ERROR -> {
                        Loader.progressDialog?.dismiss()
                    }
                }
            }
        })
    }


}