package com.iagora.wingman.app.auth.presentation.login

import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentLoginBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.Loader
import com.iagora.wingman.core.presentation.util.Util.customPrimaryColor
import com.iagora.wingman.core.util.UiEvent
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment :
    BaseFragment<FragmentLoginBinding>
        (R.layout.fragment_login, { FragmentLoginBinding.bind(it) }) {


    private val viewModel: LoginViewModel by viewModel()


    override fun setView() {
        Loader.handleLoading(requireContext())
        requestLogin()
        observerEventLogin()
    }

    private fun requestLogin() {
        binding.incBtnLogin.setOnClickListener {
            val phoneNumber = binding.tlPhone.editText?.text.toString()
            viewModel.requestLogin(phoneNumber)


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
                Timber.e("EVENT success")
//                startActivity(requireActivity() (this, MainActivity::class.java))
            }
        }
    }


}