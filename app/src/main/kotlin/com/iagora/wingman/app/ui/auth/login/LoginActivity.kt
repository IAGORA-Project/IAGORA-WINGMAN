package com.iagora.wingman.app.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.messaging.FirebaseMessaging
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iagora.wingman.app.databinding.ActivityLoginBinding
import com.iagora.wingman.app.ui.auth.AuthViewModel
import com.iagora.wingman.app.ui.main_menu.MainActivity
import com.iagora.wingman.app.utils.Loader

import com.iagora.wingman.core.source.remote.body.LoginBody
import com.iagora.wingman.helper.Status

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private val authViewModel: AuthViewModel by viewModel()
    private var deviceToken: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Loader.handleLoading(this)
        initFirebase()
        handleAction()
    }

    private fun handleAction() {
        binding.incButtonMasuk.btnPrimary.setOnClickListener {
            val phone_number =  binding.tilNomorPonsel.editText?.text.toString()
            val password =  binding.tilPassword.editText?.text.toString()
            val body = LoginBody(phone_number, password, deviceToken)


            if (phone_number.isNotEmpty() && password.isNotEmpty()) {
                login(body)
            } else {
                Toast.makeText(this, "Isi dulu nomor ponsel dan password anda.", Toast.LENGTH_SHORT)
                    .show()
            }
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

    private fun initFirebase() {
        FirebaseMessaging.getInstance().isAutoInitEnabled = true
        FirebaseMessaging.getInstance().token.addOnCompleteListener {
            try{
                deviceToken = it.result
                Log.d("hasilTOKEN","dilakukan ${it.result}")
//                it.result?.let { it1 -> sharedViewModel.SharedDeviceToken(it1) }

//                it.result?.let { it1 -> authViewModel.saveDeviceToken(it1) }
            }catch (e: Exception){
                println("ERRORGETRESULT")
            }

        }
    }


}