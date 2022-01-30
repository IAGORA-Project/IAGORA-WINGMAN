package com.iagora.wingman.gallery.presentation.camera

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.os.Handler
import android.os.HandlerThread
import android.provider.MediaStore
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import androidx.camera.core.*
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.camera.view.PreviewView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.util.SetImage.load
import com.iagora.wingman.core.presentation.util.hide
import com.iagora.wingman.core.presentation.util.setMessage
import com.iagora.wingman.core.presentation.util.show
import com.iagora.wingman.gallery.presentation.R
import com.iagora.wingman.gallery.presentation.databinding.FragmentCameraBinding
import com.iagora.wingman.gallery.presentation.util.LuminosityAnalyzer
import com.iagora.wingman.gallery.presentation.util.ThreadExecutor
import com.iagora.wingman.gallery.presentation.util.circularClose
import com.iagora.wingman.gallery.presentation.util.circularReveal
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber
import java.io.File
import java.util.concurrent.ExecutionException
import kotlin.properties.Delegates

class CameraFragment :
    BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera,
        { FragmentCameraBinding.bind(it) }) {

    private var cameraProvider: ProcessCameraProvider? = null
    private var imageCapture: ImageCapture? = null
    private var imageAnalyzer: ImageAnalysis? = null
    private var preview: Preview? = null
    private var uri: Uri? = null


    // Selector showing which camera is selected (front or back)
    private var lensFacing = CameraSelector.DEFAULT_BACK_CAMERA


    // Selector showing which flash mode is selected (on, off or auto)
    private var flashMode by Delegates.observable(ImageCapture.FLASH_MODE_OFF) { _, _, new ->
        binding.incCameraView.btnFlash.setImageResource(
            when (new) {
                ImageCapture.FLASH_MODE_ON -> R.drawable.ic_flash_on
                ImageCapture.FLASH_MODE_AUTO -> R.drawable.ic_flash_auto
                else -> R.drawable.ic_flash_off
            }
        )
    }


    /**
     * Show flashlight selection menu by circular reveal animation.
     *  circularReveal() function is an Extension function which is adding the circular reveal
     * */
    private fun selectFlash() = binding.incCameraView.apply {
        llFlashOptions.circularReveal(btnFlash)
    }

    /**
     * This function is called from XML view via Data Binding to select a FlashMode
     *  possible values are ON, OFF or AUTO
     *  circularClose() function is an Extension function which is adding circular close
     * */
    private fun closeFlashAndSelect(@ImageCapture.FlashMode flash: Int) =
        binding.incCameraView.apply {
            llFlashOptions.circularClose(btnFlash) {
                flashMode = flash
                btnFlash.setImageResource(
                    when (flash) {
                        ImageCapture.FLASH_MODE_ON -> R.drawable.ic_flash_on
                        ImageCapture.FLASH_MODE_OFF -> R.drawable.ic_flash_off
                        else -> R.drawable.ic_flash_auto
                    }
                )
            }
            imageCapture?.flashMode = flashMode
        }


    /**
     *  Detecting the most suitable aspect ratio for current dimensions
     *
     *  @param width - preview width
     *  @param height - preview height
     *  @return suitable aspect ratio
     */
    private fun aspectRatio(width: Int, height: Int): Int {
        val previewRatio = width.coerceAtLeast(height).toDouble() / width.coerceAtMost(height)
        if (kotlin.math.abs(previewRatio - RATIO_4_3_VALUE) <= kotlin.math.abs(previewRatio - RATIO_16_9_VALUE)) {
            return AspectRatio.RATIO_4_3
        }
        return AspectRatio.RATIO_16_9
    }

    private val outputDirectory: String by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            "${Environment.DIRECTORY_DCIM}/${requireActivity().packageName}/"
        } else {
            "${requireContext().getExternalFilesDir(Environment.DIRECTORY_DCIM)}/${requireActivity().packageName}/"
        }
    }


    @RequiresApi(Build.VERSION_CODES.P)
    override fun setView() {

        startCamera()
        binding.incCameraView.apply {
            btnFlash.setOnClickListener { selectFlash() }
            btnShutter.setOnClickListener { takePicture() }
            btnFlashOff.setOnClickListener { closeFlashAndSelect(ImageCapture.FLASH_MODE_OFF) }
            btnFlashOn.setOnClickListener { closeFlashAndSelect(ImageCapture.FLASH_MODE_ON) }
            btnFlashAuto.setOnClickListener { closeFlashAndSelect(ImageCapture.FLASH_MODE_AUTO) }
        }
    }


    private fun bindToLifecycle(
        localCameraProvider: ProcessCameraProvider,
        viewFinder: PreviewView,
    ) {
        try {
            localCameraProvider.bindToLifecycle(
                viewLifecycleOwner, // current lifecycle owner
                lensFacing,
                preview, // camera preview use case
                imageCapture, // image capture use case
                imageAnalyzer, // image analyzer use case
            )
            // Attach the viewfinder's surface provider to preview use case
            preview?.setSurfaceProvider(viewFinder.surfaceProvider)
        } catch (e: Exception) {
            Timber.e(TAG, "Failed to bind use cases", e)
        }
    }


    /**
     * Unbinds all the lifecycles from CameraX, then creates new with new parameters
     * */
    private fun startCamera() {
        // This is the CameraX PreviewView where the camera will be rendered
        val viewFinder = binding.incCameraView.pvCamera

        val cameraProviderFuture = ProcessCameraProvider.getInstance(requireContext())
        cameraProviderFuture.addListener({
            try {
                cameraProvider = cameraProviderFuture.get()
            } catch (e: InterruptedException) {
                setMessage("Error starting camera", binding.content)
                return@addListener
            } catch (e: ExecutionException) {
                setMessage("Error starting camera", binding.content)
                return@addListener
            }

            // The display information
            val metrics = DisplayMetrics().also { viewFinder.display.getRealMetrics(it) }
            // The ratio for the output image and preview
            val aspectRatio = aspectRatio(metrics.widthPixels, metrics.heightPixels)

            val localCameraProvider = cameraProvider
                ?: throw IllegalStateException("Camera initialization failed.")

            // The Configuration of camera preview
            preview = Preview.Builder()
                .setTargetAspectRatio(aspectRatio) // set the camera aspect ratio
                .build()

            // The Configuration of image capture
            imageCapture = ImageCapture.Builder()
                .setCaptureMode(ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY) // setting to have pictures with highest quality possible (may be slow)
                .setFlashMode(flashMode) // set capture flash
                .setTargetAspectRatio(aspectRatio) // set the capture aspect ratio
                .build()


            // The Configuration of image analyzing
            imageAnalyzer = ImageAnalysis.Builder()
                .setTargetAspectRatio(aspectRatio) // set the analyzer aspect ratio
                .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST) // in our analysis, we care about the latest image
                .build()
                .also { setLuminosityAnalyzer(it) }

            // Unbind the use-cases before rebinding them
            localCameraProvider.unbindAll()
            // Bind all use cases to the camera with lifecycle
            bindToLifecycle(localCameraProvider, viewFinder)
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    private fun setLuminosityAnalyzer(imageAnalysis: ImageAnalysis) {
        // Use a worker thread for image analysis to prevent glitches
        val analyzerThread = HandlerThread("LuminosityAnalysis").apply { start() }
        imageAnalysis.setAnalyzer(
            ThreadExecutor(Handler(analyzerThread.looper)),
            LuminosityAnalyzer()
        )
    }


    @RequiresApi(Build.VERSION_CODES.P)
    private fun takePicture() = lifecycleScope.launch(Dispatchers.Main) {
        val localImageCapture =
            imageCapture ?: throw IllegalStateException("Camera initialization failed.")

        // Options fot the output image file
        val outputOptions = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val contentValues = ContentValues().apply {
                put(MediaStore.MediaColumns.DISPLAY_NAME, System.currentTimeMillis())
                put(MediaStore.MediaColumns.MIME_TYPE, "image/jpeg")
                put(MediaStore.MediaColumns.RELATIVE_PATH, outputDirectory)
            }

            val contentResolver = requireContext().contentResolver

            // Create the output uri
            val contentUri =
                MediaStore.Images.Media.getContentUri(MediaStore.VOLUME_EXTERNAL_PRIMARY)

            ImageCapture.OutputFileOptions.Builder(contentResolver, contentUri, contentValues)
        } else {
            File(outputDirectory).mkdirs()
            val file = File(outputDirectory, "${System.currentTimeMillis()}.jpg")
            ImageCapture.OutputFileOptions.Builder(file)

        }.build()

        localImageCapture.takePicture(
            outputOptions, // the options needed for the final image
            requireContext().mainExecutor, // the executor, on which the task will run
            object :
                ImageCapture.OnImageSavedCallback { // the callback, about the result of capture process
                override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                    // This function is called if capture is successfully completed
                    outputFileResults.savedUri
                        ?.let { uri ->
                            binding.incCameraView.root.hide()
                            binding.incPreviewPhoto.apply {
                                root.show()
                                ivPreview.load(uri.toString())
                            }

                            try {
                                this@CameraFragment.uri = uri
                                binding.incPreviewPhoto.apply {
                                    btnCancel.setOnClickListener { deleteImage() }
                                    btnAccept.setOnClickListener { saveToPreviousPage(uri) }
                                }

                            } catch (e: Throwable) {
                                setMessage(e.toString(), binding.content)
                            }
                        }

                }

                override fun onError(exception: ImageCaptureException) {
                    // This function is called if there is an errors during capture process
                    val msg = "Photo capture failed: ${exception.message}"
                    setMessage(msg, binding.content)
                    exception.printStackTrace()
                }
            }
        )
    }


    private fun saveToPreviousPage(uri: Uri) {
        val typeImage = arguments?.get(ARGS)
        findNavController().previousBackStackEntry?.savedStateHandle?.set(typeImage.toString(), uri)
        findNavController().popBackStack()
    }

    override fun onBackPressed() = when {
        binding.incCameraView.llFlashOptions.isVisible -> binding.incCameraView.llFlashOptions.circularClose(
            binding.incCameraView.btnFlash)
        binding.incPreviewPhoto.root.isVisible -> deleteImage()
        else -> super.onBackPressed()
    }

    private fun deleteImage() {
        val resolver =
            requireContext().applicationContext.contentResolver
        this@CameraFragment.uri?.let {
            if (resolver.delete(it, null, null) == 1) {
                binding.incPreviewPhoto.root.hide()
                binding.incCameraView.root.show()
            }
        }
    }


    companion object {
        const val ARGS = "typeImage"
        const val KTP = "KTP_WINGMAN"
        const val SKCK = "SKCK_WINGMAN"
        private const val RATIO_4_3_VALUE = 4.0 / 3.0 // aspect ratio 4x3
        private const val RATIO_16_9_VALUE = 16.0 / 9.0 // aspect ratio 16x9
    }
}