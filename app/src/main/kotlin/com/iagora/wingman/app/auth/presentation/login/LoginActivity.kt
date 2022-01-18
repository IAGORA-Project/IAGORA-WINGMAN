package com.iagora.wingman.app.auth.presentation.login

import android.content.Intent
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.databinding.ActivityLoginBinding
import com.iagora.wingman.app.main_menu.presentation.MainActivity
import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.commons.views.helper.Loader
import com.iagora.wingman.commons.views.helper.Util.customPrimaryColor
import com.iagora.wingman.core.util.UiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginActivity : BaseActivity<ActivityLoginBinding>({ ActivityLoginBinding.inflate(it) }) {


    private val viewModel: LoginViewModel by viewModel()


    override fun setView() {
        Loader.handleLoading(this)
        requestLogin()
        observerEventLogin()
    }

    private fun requestLogin() {
        binding.incButtonMasuk.btnPrimary.setOnClickListener {
            val phoneNumber = binding.tilNomorPonsel.editText?.text.toString()
            val password = binding.tilPassword.editText?.text.toString()
            viewModel.requestLogin(phoneNumber, password)


        }
        viewModel.loginState.collectWhenStarted(this) { state ->
            Timber.e("STATE $state")

            if (state.isLoading) Loader.progressDialog?.show()
            else Loader.progressDialog?.dismiss()
        }

    }

    private fun observerEventLogin() = viewModel.eventFlow.collectWhenStarted(this) { event ->

        when (event) {
            is UiEvent.CreateMessage -> {
                Snackbar.make(binding.root, event.uiText.toString(), Snackbar.LENGTH_SHORT).apply {
                    customPrimaryColor(this.context)
                }.show()
                Timber.e("EVENT ${event.uiText}")
            }
            is UiEvent.OnLogin -> {
                startActivity(Intent(this, MainActivity::class.java))
            }
        }
    }


}