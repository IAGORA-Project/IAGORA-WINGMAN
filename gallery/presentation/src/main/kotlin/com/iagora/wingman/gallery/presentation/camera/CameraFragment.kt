package com.iagora.wingman.gallery.presentation.camera

import android.annotation.SuppressLint
import android.net.Uri
import android.os.Handler
import android.os.Looper
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.util.CameraPermission.DELAY_SETUP_CAMERA
import com.iagora.wingman.core.presentation.util.CameraPermission.FILE_NAME_FORMAT
import com.iagora.wingman.core.presentation.util.CameraPermission.checkPermissions
import com.iagora.wingman.core.presentation.util.CameraPermission.requestPermission
import com.iagora.wingman.core.presentation.util.Permission.forceFullOpenPermission
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import com.iagora.wingman.core.presentation.util.hide
import com.iagora.wingman.core.presentation.util.show
import com.iagora.wingman.gallery.domain.models.Image
import com.iagora.wingman.gallery.presentation.R
import com.iagora.wingman.gallery.presentation.databinding.FragmentCameraBinding
import timber.log.Timber
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.Executors
import kotlin.random.Random

class CameraFragment :
    BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera,
        { FragmentCameraBinding.bind(it) }) {

    private val photoTaken = mutableListOf<Image>()
    private lateinit var imageTakenAdapter: ImageTakenAdapter
    private var imagePath = 0
    private lateinit var imageCapture: ImageCapture


    @SuppressLint("ClickableViewAccessibility")
    override fun setView() {
        Handler(Looper.getMainLooper()).postDelayed({
            setupCamera()
        }, DELAY_SETUP_CAMERA)

    }

    override fun onResume() {
        super.onResume()
        changePermissionViewTo(checkPermissions(requireContext()))
    }

    override fun onStop() {
        super.onStop()
        Executors.newSingleThreadExecutor().shutdown()
    }


//    override fun setAdapter() {
//        imageTakenAdapter = ImageTakenAdapter()
//        imageTakenAdapter.apply {
//            submitList(photoTaken)
//            getItemDeleted { item ->
//                photoTaken.remove(item)
//            }
//        }
//        binding.rvListPhoto.apply {
//            addItemDecoration(GridSpacingItemDecoration(9, 10, false))
//            adapter = imageTakenAdapter
//        }
//    }


    private fun setupCamera() {
        if (!checkPermissions(requireContext())) {
            requestPermission(requireActivity())
        }

        buildCamera()
        binding.apply {
            incCameraView.btnPermissionAccess.setOnClickListener {
                forceFullOpenPermission(requireActivity())
            }
            btnShutter.setOnClickListener {
                takePhoto()
            }
        }
    }

    private fun changePermissionViewTo(visible: Boolean) {
        binding.incCameraView.apply {
            tvInformationPermission.isVisible = !visible
            btnPermissionAccess.isVisible = !visible
        }
        changeMainViewTo(visible)
    }

    private fun buildCamera() {
        imageCapture = ImageCapture.Builder().build()
        val cameraProviderFuture = ProcessCameraProvider
            .getInstance(requireContext())

        cameraProviderFuture.addListener({
            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
            val preview = Preview.Builder()
                .build()
                .also { mPreview ->
                    mPreview.setSurfaceProvider(
                        binding.incCameraView.pvCamera.surfaceProvider
                    )
                }


            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    this,
                    cameraSelector,
                    preview,
                    imageCapture
                )
            } catch (e: Exception) {
                Snackbar.make(binding.content, e.toString(), Snackbar.LENGTH_SHORT)
                    .apply { customPrimaryColor(this.context) }.show()
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun setupOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let { mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }
        return if (mediaDir != null && mediaDir.exists()) mediaDir else requireActivity().filesDir
    }

    private fun takePhoto() {
        binding.shutterEffect.isVisible = true
        changeMainViewTo(false)
        val photoFile = File(
            setupOutputDirectory(),
            SimpleDateFormat(
                FILE_NAME_FORMAT + Random.nextInt(), Locale.getDefault()
            ).format(System.currentTimeMillis()) + ".jpg"
        )


        val outputOption = ImageCapture
            .OutputFileOptions
            .Builder(photoFile)
            .build()


        imageCapture.takePicture(
            outputOption, ContextCompat.getMainExecutor(requireContext()),
            object : ImageCapture.OnImageSavedCallback {

                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    val imageUri = Uri.fromFile(photoFile)


                    Timber.e(photoFile.toString())
                    Timber.e(imageUri.toString())

                    photoTaken.add(
                        Image(
                            imagePath = imageUri.path.toString(),
                            imageName = photoFile.name,
                            imageUri = imageUri
                        )
                    )

                    binding.shutterEffect.hide()
                    binding.incCameraView.root.hide()
                    binding.ivPreview.show()
                    binding.ivPreview.setImageURI(imageUri)

//                    changeMainViewTo(true)
                }

                override fun onError(exception: ImageCaptureException) {
                    setMessage(exception.toString())
                    binding.shutterEffect.isVisible = false
                    changeMainViewTo(true)
                }

            }
        )


    }

    private fun changeMainViewTo(visible: Boolean) {
        binding.apply {
            btnShutter.isVisible = visible
//            btnSwipe.isVisible = visible
//            incHeader.root.isVisible = visible
//            rvListPhoto.isVisible = visible
        }
    }


    private fun setMessage(message: String) {
        Snackbar.make(
            binding.content,
            message,
            Snackbar.LENGTH_SHORT
        ).apply { customPrimaryColor(context) }.show()
    }

}