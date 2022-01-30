package com.iagora.wingman.app.auth.presentation.register

import android.net.Uri
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.google.android.material.imageview.ShapeableImageView
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentRegisterBinding
import com.iagora.wingman.app.databinding.LayoutRegisterSetPersonalInfoBinding
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.core.presentation.util.hide
import com.iagora.wingman.core.presentation.util.setMessage
import com.iagora.wingman.core.presentation.util.show
import com.iagora.wingman.core.util.AuthError
import com.iagora.wingman.gallery.presentation.camera.CameraFragment.Companion.KTP
import com.iagora.wingman.gallery.presentation.camera.CameraFragment.Companion.SKCK
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


class RegisterFragment :
    BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register,
        { FragmentRegisterBinding.bind(it) }) {

    private val viewModel: RegisterViewModel by viewModel()

    override fun setView() {
        requestToSendDocument()
        requestBackToPersonalInfo()
        getImageFromCamera()
        observerEventRegister()
    }

    private fun requestToSendDocument() {
        binding.incSetPersonalInfo.btnNextToDocument.setOnClickListener { sendPersonalInfo() }
    }

    private fun requestBackToPersonalInfo() {
        binding.incDocument.btnBackToPersonal.setOnClickListener { viewModel.toPersonalInfo() }
    }

    private fun getImageFromCamera() {
        binding.incDocument.apply {
            svKTP.setOnClickListener { openCamera(KTP) }
            svSKCK.setOnClickListener { openCamera(SKCK) }

            findNavController().currentBackStackEntryFlow.collectLatestWhenStarted(
                viewLifecycleOwner) { nav ->
                nav.savedStateHandle.get<Uri>(KTP)?.let { uriKTP ->
                    setImageToPlaceHolder(svKTP, uriKTP)
                }
                nav.savedStateHandle.get<Uri>(SKCK)?.let { uriSKCK ->
                    setImageToPlaceHolder(svSKCK, uriSKCK)
                }
            }
        }
    }

    private fun setImageToPlaceHolder(sv: ShapeableImageView, uri: Uri) {
        sv.apply {
            setContentPadding(0, 0, 0, 0)
            load(uri.toString())
        }
    }

    private fun openCamera(typeImage: String) =
        findNavController().navigate(RegisterFragmentDirections.openCamera(typeImage))

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

        viewModel.registerState.collectLatestWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                RegisterState.OnSetDocument -> {
                    binding.apply {
                        incSetPersonalInfo.root.hide()
                        incDocument.root.show()
                    }
                }
                RegisterState.OnSetPersonalInfo -> {
                    binding.apply {
                        incSetPersonalInfo.root.show()
                        incDocument.root.hide()
                    }
                }
                RegisterState.OnPreview -> {

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

    override fun onBackPressed() = when {
        binding.incDocument.root.isVisible -> viewModel.toPersonalInfo()
        else -> requireActivity().finish()
    }
}


