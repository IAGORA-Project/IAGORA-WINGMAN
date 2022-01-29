package com.iagora.wingman.app.auth.presentation.register

import androidx.navigation.fragment.findNavController
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentRegisterBinding
import com.iagora.wingman.app.databinding.LayoutRegisterSetPersonalInfoBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.hide
import com.iagora.wingman.core.presentation.util.show
import com.iagora.wingman.core.util.AuthError
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class RegisterFragment :
    BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register,
        { FragmentRegisterBinding.bind(it) }) {

    private val viewModel: RegisterViewModel by viewModel()

    override fun setView() {
        requestToSendDocument()
        requestBackToPersonalInfo()
        observerEventRegister()
        getImageFromCamera()
    }

    private fun requestToSendDocument() {
        binding.incSetPersonalInfo.btnNextToDocument.setOnClickListener { sendPersonalInfo() }
    }

    private fun requestBackToPersonalInfo() {
        binding.incDocument.btnBackToPersonal.setOnClickListener { viewModel.backToPersonalInfo() }
    }

    private fun getImageFromCamera() {
        binding.incDocument.apply {
            svKTP.setOnClickListener { findNavController().navigate(R.id.openCamera) }
        }
    }

    private fun sendPersonalInfo() {
        binding.incSetPersonalInfo.apply {
            val fullName = etFullName.text.toString()
            val address = etAddress.text.toString()
            val email = etEmail.text.toString()

            viewModel.personalInfo(fullName, email, address)
        }
    }

    private fun observerEventRegister() {
        binding.incSetPersonalInfo.personalInfoObserveError()

        viewModel.eventFlow.collectWhenStarted(viewLifecycleOwner) { event ->

            Timber.e(event.toString())
            when (event) {
                RegisterEvent.OnSetDocument -> {
                    binding.apply {
                        incSetPersonalInfo.root.hide()
                        incDocument.root.show()
                    }
                }
                RegisterEvent.OnSetPersonalInfo -> {
                    binding.apply {
                        incSetPersonalInfo.root.show()
                        incDocument.root.hide()
                    }
                }

            }
        }
    }

    private fun LayoutRegisterSetPersonalInfoBinding.personalInfoObserveError() {

        viewModel.fullNameError.collectLatestWhenStarted(viewLifecycleOwner) { errorType ->
            etFullName.error = commonError(errorType is AuthError.FieldEmpty)
        }

        viewModel.addressError.collectLatestWhenStarted(viewLifecycleOwner) { errorType ->
            etAddress.error = commonError(errorType is AuthError.FieldEmpty)
        }

        viewModel.emailError.collectWhenStarted(viewLifecycleOwner) { errorType ->
            Timber.e(errorType.toString())
            etEmail.error = when (errorType) {
                is AuthError.FieldEmpty -> resources.getString(R.string.error_field_empty)
                is AuthError.InvalidEmail -> resources.getString(R.string.error_invalid_email)
                else -> null
            }
        }


    }

    private fun commonError(error: Boolean) = if (error)
        resources.getString(R.string.error_field_empty) else null
}


