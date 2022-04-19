package com.iagora.wingman.app.auth.presentation.login

import android.content.Intent
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.presentation.on_boarding.OnBoardingPage
import com.iagora.wingman.app.databinding.FragmentLoginBinding
import com.iagora.wingman.app.main_menu.presentation.MainActivity
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.*
import com.iagora.wingman.core.util.AuthError
import okhttp3.internal.format
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class LoginFragment :
    BaseFragment<FragmentLoginBinding>
        (R.layout.fragment_login, { FragmentLoginBinding.bind(it) }) {

    private val viewModel: LoginViewModel by viewModel()
    private var phone: String? = null

    override fun setView() {
        Loader.handleLoading(requireContext())

        requestOTP()
        requestLogin()
        observerEventLogin()
        observerEventSuccesFlow()
        setupNavigation()

        observerEventBoardingPage()
        verifPhone()
        showKeyboard()
    }

    private fun observerEventBoardingPage() {
        viewModel.onBoardingpage.collectLatestWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                OnBoardingPage.MainPage -> {
                    startActivity(
                        Intent(
                            context,
                            MainActivity::class.java
                        ).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                            .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    )
                }
                else -> {}
            }
        }
    }

    fun verifPhone() {
        binding.incSetOtp.otpView.setOtpCompletionListener { otp ->
            phone?.let {
                viewModel.verifyOtp(it, otp)
                binding.incSetOtp.otpView.text = null
                hideKeyboard()
            }
        }
    }

    private fun requestOTP() {
        binding.apply {
            incSetPhone.apply {
                incBtnLogin.btnAction.setOnClickListener { sendPhoneNumber() }
                tlPhone.editText?.performSendAction { sendPhoneNumber() }
            }
            incSetOtp.btnDescStateOtp.setOnClickListener { sendPhoneNumber();resetFieldOtp() }
        }
    }

    private fun sendPhoneNumber() {
        val phoneNumber = binding.incSetPhone.tlPhone.editText?.text.toString()
        viewModel.setPhoneNumber(phoneNumber)
    }

    private fun resetFieldOtp() {
        binding.incSetOtp.otpView.text?.clear()
    }

    private fun requestLogin() {
        binding.incSetOtp.otpView.setOtpCompletionListener(viewModel::login)
    }

    private fun setTextDescStateOtp(desc: String, isEnable: Boolean = false) {
        binding.incSetOtp.btnDescStateOtp.apply {
            text = desc
            isEnabled = isEnable
        }
    }

    private fun observerEventSuccesFlow() {
        viewModel.eventSuccesFlow.collectWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                is UiEvent.CreateMessage -> {
                    Snackbar.make(
                        binding.root,
                        event.uiText.asString(requireContext()),
                        Snackbar.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun observerEventLogin() {
        viewModel.phoneNumberError.collectLatestWhenStarted(viewLifecycleOwner) { errorType ->
            binding.incSetPhone.tlPhone.error = when (errorType) {
                is AuthError.FieldEmpty -> resources.getString(R.string.error_field_empty)
                is AuthError.InvalidPhoneNumber -> resources.getString(R.string.error_invalid_phone_number)
                else -> ""
            }
        }

        viewModel.phoneNumberCompleted.collectLatestWhenStarted(viewLifecycleOwner) { phoneNumber ->
            phone = phoneNumber
            binding.incSetOtp.tvDesc.text =
                format(resources.getString(R.string.otp_set_desc), phoneNumber)
        }

        viewModel.loginState.collectLatestWhenStarted(viewLifecycleOwner) { isLoading ->
            if (isLoading) Loader.progressDialog?.show()
            else Loader.progressDialog?.dismiss()
        }

        viewModel.delayToResendOtp.collectLatestWhenStarted(viewLifecycleOwner) { time ->
            if (time > 0) {
                setTextDescStateOtp(format(resources.getString(R.string.desc_state_otp), time))
            } else {
                setTextDescStateOtp(resources.getString(R.string.resend_otp), true)
            }
        }

        viewModel.eventFlow.collectLatestWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                is UiEvent.CreateMessage -> {
                    Snackbar.make(
                        binding.root,
                        event.uiText.asString(requireContext()),
                        Snackbar.LENGTH_SHORT
                    ).apply {
                        customPrimaryColor(this.context)
                    }.show()
                }
                is LoginEvent.OnGetOtp -> {
                    with(binding) {
                        incSetPhone.root.hide()
                        incSetOtp.root.show()
                    }
                    showKeyboard()
                }

                is LoginEvent.OnLogin -> {
                    with(binding) {
                        incSetPhone.root.show()
                        incSetOtp.root.hide()
                    }
                }
            }
        }
    }

    private fun setupNavigation() {
        binding.incSetPhone.tvRegister.setOnClickListener { findNavController().navigate(R.id.toRegister) }
    }

    override fun onBackPressed() = requireActivity().finish()
}