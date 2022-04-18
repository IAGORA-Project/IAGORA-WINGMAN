package com.iagora.wingman.app.auth.presentation.register

import android.content.Intent
import android.database.Cursor
import android.net.Uri
import android.provider.MediaStore
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.app.R
import com.iagora.wingman.app.auth.data.remote.request.RegistrationReq
import com.iagora.wingman.app.auth.presentation.on_boarding.OnBoardingPage
import com.iagora.wingman.app.auth.presentation.on_boarding.OnBoardingViewModel
import com.iagora.wingman.app.databinding.FragmentRegisterBinding
import com.iagora.wingman.app.databinding.LayoutRegisterSetPersonalInfoBinding
import com.iagora.wingman.app.main_menu.presentation.MainActivity
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectLatestWhenStarted
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.*
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.core.util.AuthError
import com.iagora.wingman.gallery.presentation.camera.CameraFragment.Companion.KTP
import com.iagora.wingman.gallery.presentation.camera.CameraFragment.Companion.SKCK
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber
import java.io.ByteArrayOutputStream
import java.io.IOException
import java.io.InputStream


class RegisterFragment :
    BaseFragment<FragmentRegisterBinding>(R.layout.fragment_register,
        { FragmentRegisterBinding.bind(it) }) {

    private val viewModel: RegisterViewModel by viewModel()
    private var typeImageCurrent: String? = null
    private var imageKtp: ByteArray? = null
    private var imageSkck: ByteArray? = null
    private var uriKtp: Uri? = null
    private var uriSkck: Uri? = null
    private var phone: String? = null

    override fun setView() {
        verifPhone()
        requestToSendDocument()
        requestBackToPersonalInfo()
        getImageFromCamera()

        observerEventRegister()
        observerEventFlow()
        observerEventSuccesFlow()
        observerDetailSuccess()
        observerEventBoardingPage()

        initListCity()

        binding.incDocument.btnNextToPreview.setOnClickListener {
            viewModel.toPreview()
        }

    }


    fun initListCity() {
        val itemlist = listOf("Medan", "Yogyakarta")
        val adapterCity =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemlist)
        binding.incSetPersonalInfo.autoCompleteCity.setAdapter(adapterCity)

        val itemBank = listOf("Digital BCA", "Mandiri")
        val adaperBank =
            ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, itemBank)
        binding.incDocument.atvBank.setAdapter(adaperBank)
    }


    private fun sendPhoneVerification() {
        binding.incVerifSetPhone.apply {
            phone = tlPhone.editText?.text.toString()
            viewModel.setPhoneNumber(phone!!)
            hideKeyboard()
        }
    }

    fun verifPhone() {
        binding.incVerifSetPhone.btnAction.setOnClickListener { sendPhoneVerification() }

        binding.incVerifSetOtp.otpView.setOtpCompletionListener { otp ->
            viewModel.verifyOtp(phone!!, otp!!)
            binding.incVerifSetOtp.otpView.text = null
            hideKeyboard()
        }
    }

    private fun requestToSendDocument() {
        binding.incSetPersonalInfo.btnNextToDocument.setOnClickListener {
            viewModel.toDocument()
        }
    }

    private fun requestBackToPersonalInfo() {
        binding.incDocument.btnBackToPersonal.setOnClickListener { viewModel.toPersonalInfo() }
    }


    private fun getImageFromCamera() {
        binding.incDocument.apply {
            svKTP.setOnClickListener { openCamera(KTP) }
            svSKCK.setOnClickListener { openCamera(SKCK) }

            findNavController().currentBackStackEntryFlow.collectLatestWhenStarted(
                viewLifecycleOwner
            ) { nav ->
                nav.savedStateHandle.get<Uri>(KTP)?.let { uriKTP ->
                    setImageToPlaceHolder(svKTP, uriKTP)
                    uriKtp = uriKTP
                    imageKtp = setTempFile(uriKTP)


                }
                nav.savedStateHandle.get<Uri>(SKCK)?.let { uriSKCK ->
                    setImageToPlaceHolder(svSKCK, uriSKCK)
                    uriSkck = uriSKCK
                    imageSkck = setTempFile(uriSKCK)
                }
            }
        }
    }


    private fun setTempFile(uri: Uri): ByteArray {
        val inputStream = requireContext().contentResolver.openInputStream(uri)
        val outputStream = ByteArrayOutputStream()
        inputStream!!.copyTo(outputStream, minOf(1024, inputStream.available()))
        return outputStream.toByteArray() ?: byteArrayOf(0)
    }

    private fun setImageToPlaceHolder(sv: ShapeableImageView, uri: Uri) {
        sv.apply {
            setContentPadding(0, 0, 0, 0)
            load(uri.toString())
        }
    }

    private fun openCamera(typeImage: String) {
        if (!Permission.hasPermission(requireActivity(), android.Manifest.permission.CAMERA)) {
            typeImageCurrent = typeImage
            launcherPermission.launch(android.Manifest.permission.CAMERA)
        } else {
            findNavController().navigate(RegisterFragmentDirections.openCamera(typeImage))
        }

    }


    private fun sendPersonalInfo() {
        binding.incSetPersonalInfo.apply {
            val name = etFullName.text.toString()
            val email = etEmail.text.toString()
            val address = etAddress.text.toString()
            val city = autoCompleteCity.text.toString()

            viewModel.registerDetail(RegistrationReq(name, email, address, city))
        }
    }

    private fun sendRegisterComplate() {
        binding.incDocument.apply {
            val map: HashMap<String, RequestBody> = HashMap()
            map.put("no_rek", createPartFromString(etNoRek.text.toString()))
            map.put("nama_rek", createPartFromString(etNameRek.text.toString()))
            map.put("bank", createPartFromString(atvBank.text.toString()))

            val bodyktp =
               imageKtp?.let {
                   MultipartBody.Part.createFormData(
                       "ktp",
                       "${System.currentTimeMillis()}.jpg",
                       it.toRequestBody("image/JPG".toMediaTypeOrNull())
                   )
               }

            val bodySkck =
                imageSkck?.let {
                    MultipartBody.Part.createFormData(
                        "skck",
                        "${System.currentTimeMillis()}.jpg",
                        imageSkck!!.toRequestBody("image/JPG".toMediaTypeOrNull())
                    )
                }

            if (bodySkck!= null && bodyktp != null){
                viewModel.registerComplete(bodyktp, bodySkck, map)
            }else{
                if (bodyktp == null){
                    viewModel.parsingErrorMessage("KTP belum diisi")
                }else if (bodySkck == null){
                    viewModel.parsingErrorMessage("SKCK belum diisi")

                }

            }


        }

    }

    fun createPartFromString(desc: String): RequestBody {
        return desc.toRequestBody(MultipartBody.FORM)
    }

    private fun observerDetailSuccess() {
        viewModel.registerDetailSuccess.observe(viewLifecycleOwner) {
            it?.let {
                sendRegisterComplate()
            }
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

    private fun observerEventFlow() {
        viewModel.eventFlow.collectWhenStarted(viewLifecycleOwner) { event ->
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
            }
        }
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

    private fun observerEventRegister() {
        binding.incSetPersonalInfo.personalInfoObserveError()

        viewModel.registerState.collectLatestWhenStarted(viewLifecycleOwner) { event ->
            when (event) {
                RegisterState.OnSetPhoneOtp -> {
                    binding.apply {
                        incVerifSetPhone.root.show()
                        incVerifSetOtp.root.hide()
                        incSetPersonalInfo.root.hide()
                        incDocument.root.hide()
                        incReview.root.hide()
                    }
                }
                RegisterState.OnSetVerifOtp -> {
                    binding.apply {
                        incVerifSetPhone.root.hide()
                        incVerifSetOtp.root.show()
                        incSetPersonalInfo.root.hide()
                        incDocument.root.hide()
                        incReview.root.hide()

                        incVerifSetOtp.otpView.requestFocus()
                        context?.showKeyboard(incVerifSetOtp.otpView)
                    }


                }
                RegisterState.OnSetPersonalInfo -> {
                    binding.apply {
                        incVerifSetPhone.root.hide()
                        incVerifSetOtp.root.hide()
                        incSetPersonalInfo.root.show()
                        incDocument.root.hide()
                        incReview.root.hide()
                    }
                }
                RegisterState.OnSetDocument -> {
                    binding.apply {
                        incVerifSetPhone.root.hide()
                        incVerifSetOtp.root.hide()
                        incSetPersonalInfo.root.hide()
                        incDocument.root.show()
                        incReview.root.hide()
                    }
                }

                RegisterState.OnPreview -> {
                    binding.apply {
                        incVerifSetPhone.root.hide()
                        incVerifSetOtp.root.hide()
                        incSetPersonalInfo.root.hide()
                        incDocument.root.hide()
                        incReview.root.show()
                        initPreview()
                    }
                }

            }
        }
    }

    fun initPreview() {
        binding.incReview.apply {
            tvFullname.text = binding.incSetPersonalInfo.etFullName.text.toString()
            tvEmail.text = binding.incSetPersonalInfo.etEmail.text.toString()
            tvTelp.text = binding.incVerifSetPhone.etTelp.text.toString()
            tvAddress.text = binding.incSetPersonalInfo.etAddress.text.toString()
            tvBank.text = binding.incDocument.atvBank.text.toString()
            tvNameRek.text = binding.incDocument.etNameRek.text.toString()
            tvNorek.text = binding.incDocument.etNoRek.text.toString()

            imgKtp.apply {
                load(uriKtp.toString())
            }

            imgSkck.apply {
                load(uriSkck.toString())
            }

            btnProcess.setOnClickListener {
                sendPersonalInfo()
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
        binding.incVerifSetOtp.root.isVisible -> viewModel.toSeNumbertOtp()
        binding.incSetPersonalInfo.root.isVisible -> viewModel.toSeNumbertOtp()
        binding.incDocument.root.isVisible -> viewModel.toPersonalInfo()
        binding.incReview.root.isVisible -> viewModel.toDocument()
        else -> super.onBackPressed()
    }

    val launcherPermission =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) {
            if (it) {
                openCamera(typeImageCurrent!!)
                Toast.makeText(context, "Permission Granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(context, "Permission Denied", Toast.LENGTH_SHORT).show()
            }
        }
}



