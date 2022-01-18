//package com.iagora.wingman.gallery.presentation.camera
//
//import android.Manifest
//import android.annotation.SuppressLint
//import android.content.pm.PackageManager
//import android.net.Uri
//import android.os.Handler
//import android.os.Looper
//import androidx.camera.core.CameraSelector
//import androidx.camera.core.ImageCapture
//import androidx.camera.core.ImageCaptureException
//import androidx.camera.core.Preview
//import androidx.camera.lifecycle.ProcessCameraProvider
//import androidx.core.app.ActivityCompat
//import androidx.core.content.ContextCompat
//import androidx.core.view.isVisible
//import com.google.android.material.snackbar.Snackbar
//import com.iagora.wingman.commons.ui.base.BaseActivity
//import com.iagora.wingman.commons.views.helper.GridSpacingItemDecoration
//import com.iagora.wingman.commons.views.helper.OnSwipeTouchListener
//import com.iagora.wingman.commons.views.helper.Permission.forceFullOpenPermission
//import com.iagora.wingman.commons.views.helper.Util.customPrimaryColor
//import com.iagora.wingman.gallery.domain.models.Image
//import com.iagora.wingman.gallery.presentation.R
//import com.iagora.wingman.gallery.presentation.album.AlbumFragment
//import com.iagora.wingman.gallery.presentation.album.AlbumFragment.Companion.MAX_IMAGE
//import com.iagora.wingman.gallery.presentation.databinding.ActivityGalleryBinding
//import java.io.File
//import java.text.SimpleDateFormat
//import java.util.*
//import java.util.concurrent.Executors
//import kotlin.random.Random
//
//class GalleryActivity :
//    BaseActivity<ActivityGalleryBinding>({ ActivityGalleryBinding.inflate(it) }) {
//
//    private val photoTaken = mutableListOf<Image>()
//    private lateinit var imageTakenAdapter: ImageTakenAdapter
//    private var imagePath = 0
//    private lateinit var imageCapture: ImageCapture
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    override fun setView() {
//        Handler(Looper.getMainLooper()).postDelayed({
//            setupCamera()
//        }, DELAY_SETUP_CAMERA)
//        setupMoveToAlbum()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        changePermissionViewTo(checkPermissions())
//    }
//
//    override fun onStop() {
//        super.onStop()
//        Executors.newSingleThreadExecutor().shutdown()
//    }
//
//
//    override fun setupAdapter() {
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
//
//
//    override fun setupToolbar() {
//        binding.incHeader.apply {
//            btnCloseGallery.setOnClickListener {
//                finish()
//            }
//        }
//    }
//
//    private fun setupCamera() {
//        if (!checkPermissions()) {
//
//        }
//
//        buildCamera()
//        binding.apply {
//            incCameraView.btnPermissionAccess.setOnClickListener {
//                forceFullOpenPermission(this@GalleryActivity)
//            }
//            btnShutter.setOnClickListener {
//                takePhoto()
//            }
//        }
//    }
//
//    private fun changePermissionViewTo(visible: Boolean) {
//        binding.incCameraView.apply {
//            tvInformationPermission.isVisible = !visible
//            btnPermissionAccess.isVisible = !visible
//        }
//        changeMainViewTo(visible)
//    }
//
//    private fun buildCamera() {
//        imageCapture = ImageCapture.Builder().build()
//        val cameraProviderFuture = ProcessCameraProvider
//            .getInstance(this)
//
//        cameraProviderFuture.addListener({
//            val cameraProvider: ProcessCameraProvider = cameraProviderFuture.get()
//            val preview = Preview.Builder()
//                .build()
//                .also { mPreview ->
//                    mPreview.setSurfaceProvider(
//                        binding.incCameraView.pvCamera.surfaceProvider
//                    )
//                }
//
//
//            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA
//
//            try {
//                cameraProvider.unbindAll()
//                cameraProvider.bindToLifecycle(
//                    this,
//                    cameraSelector,
//                    preview,
//                    imageCapture
//                )
//            } catch (e: Exception) {
//                Snackbar.make(binding.content, e.toString(), Snackbar.LENGTH_SHORT)
//                    .apply { customPrimaryColor(this.context) }.show()
//            }
//        }, ContextCompat.getMainExecutor(this))
//    }
//
//    private fun setupOutputDirectory(): File {
//        val mediaDir = this.externalMediaDirs.firstOrNull()?.let { mFile ->
//            File(mFile, resources.getString(R.string.app_name)).apply {
//                mkdirs()
//            }
//        }
//        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
//    }
//
//    private fun takePhoto() {
//        if (imageTakenAdapter.itemCount < MAX_IMAGE) {
//            binding.shutterEffect.isVisible = true
//            changeMainViewTo(false)
//            val photoFile = File(
//                setupOutputDirectory(),
//                SimpleDateFormat(
//                    FILE_NAME_FORMAT + Random.nextInt(), Locale.getDefault()
//                ).format(System.currentTimeMillis()) + ".jpg"
//            )
//
//
//            val outputOption = ImageCapture
//                .OutputFileOptions
//                .Builder(photoFile)
//                .build()
//
//
//            imageCapture.takePicture(
//                outputOption, ContextCompat.getMainExecutor(this),
//                object : ImageCapture.OnImageSavedCallback {
//
//                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
//                        val imageUri = Uri.fromFile(photoFile)
//
//
//                        photoTaken.add(
//                            Image(
//                                imagePath = imageUri.path.toString(),
//                                imageName = photoFile.name,
//                                imageUri = imageUri
//                            )
//                        )
//                        imageTakenAdapter.notifyItemInserted(imagePath)
//                        imagePath += 1
//                        binding.shutterEffect.isVisible = false
//                        changeMainViewTo(true)
//                    }
//
//                    override fun onError(exception: ImageCaptureException) {
//                        setMessage(exception.toString())
//                        binding.shutterEffect.isVisible = false
//                        changeMainViewTo(true)
//                    }
//
//                }
//            )
//
//        } else {
//            setMessage(resources.getString(R.string.text_max_image_seleted))
//        }
//    }
//
//    private fun changeMainViewTo(visible: Boolean) {
//        binding.apply {
//            btnShutter.isVisible = visible
//            btnSwipe.isVisible = visible
//            incHeader.root.isVisible = visible
//            rvListPhoto.isVisible = visible
//        }
//    }
//
//
//    private fun setMessage(message: String) {
//        Snackbar.make(
//            binding.content,
//            message,
//            Snackbar.LENGTH_SHORT
//        ).apply { customPrimaryColor(context) }.show()
//    }
//
//
//
//    @SuppressLint("ClickableViewAccessibility")
//    private fun setupMoveToAlbum() {
//        val addFragment = AlbumFragment()
//
//        binding.incCameraView.pvCamera.setOnTouchListener(object :
//            OnSwipeTouchListener(this@GalleryActivity) {
//            override fun onSwipeUp() {
//                addFragment.show(
//                    supportFragmentManager, addFragment.tag
//                )
//            }
//        })
//    }
//
//
//
//}