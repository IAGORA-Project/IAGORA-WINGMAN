package com.iagora.wingman.app.ui.album_camera.camera


import android.content.pm.PackageManager
import android.net.Uri
import android.view.View
import android.widget.Toast
import androidx.camera.core.CameraSelector
import androidx.camera.core.ImageCapture
import androidx.camera.core.ImageCaptureException
import androidx.camera.core.Preview
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentCameraBinding
import com.iagora.wingman.app.utils.Constants
import com.iagora.wingman.commons.views.helper.Loader
import com.iagora.wingman.app.utils.Permission
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.core.source.local.model.ImageModel
import com.iagora.wingman.core.source.local.shared_view_model.SharedViewModel
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.collections.ArrayList
import kotlin.random.Random


class CameraFragment : BaseFragment<FragmentCameraBinding>(R.layout.fragment_camera,{ FragmentCameraBinding.bind(it)}),
    PhotoListAdapter.ItemCallBackAdapter {


    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var takePhotos: ArrayList<ImageModel> = ArrayList()
    private lateinit var photoListAdapter: PhotoListAdapter
    private val sharedViewModel: SharedViewModel by viewModel()

    override fun setView() {
        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDirectory = getOutputDirectory()

        Loader.handleLoading(requireContext())

        if(!checkPermissions()){
            ActivityCompat.requestPermissions(
                requireActivity(), Constants.REQUIRED_PERMISSIONS,
                Constants.REQUEST_CODE_PERMISSIONS
            )
        }

        binding.btnShutter.setOnClickListener {
            takePhoto()
        }
    }



    override fun onResume() {
        super.onResume()

        if(checkPermissions()){
            startCamera()
            binding.incCameraView.tvInformationPermission.visibility = View.GONE
            binding.incCameraView.tvPermissionAccess.visibility = View.GONE
            binding.btnShutter.visibility = View.VISIBLE
            println("OKEEEEEAKSESS")
        }else{
            binding.incCameraView.tvInformationPermission.visibility = View.VISIBLE
            binding.incCameraView.tvPermissionAccess.visibility = View.VISIBLE
            binding.btnShutter.visibility = View.GONE
            binding.incCameraView.tvPermissionAccess.setOnClickListener {
                Permission.forceFullOpenPermission(requireActivity())
            }
        }
    }



    private fun startCamera() {
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

            imageCapture = ImageCapture.Builder()
                .build()

            val cameraSelector = CameraSelector.DEFAULT_BACK_CAMERA

            try {
                cameraProvider.unbindAll()
                cameraProvider.bindToLifecycle(
                    requireActivity(),
                    cameraSelector,
                    preview,
                    imageCapture
                )
            }catch (e: Exception){
                println("ERRORCAMERA $e")
            }
        }, ContextCompat.getMainExecutor(requireContext()))
    }

    // Membuat Folder
    private fun getOutputDirectory(): File {
        val mediaDir = requireActivity().externalMediaDirs.firstOrNull()?.let {mFile ->
            File(mFile, resources.getString(R.string.app_name)).apply {
                mkdirs()
            }
        }

        return if(mediaDir != null && mediaDir.exists()) mediaDir else requireActivity().filesDir
    }



    private fun takePhoto() {
        if(takePhotos.size < 9) {
            Loader.progressDialog?.show()
            val imageCapture = imageCapture ?: return
            val photoFile = File(
                outputDirectory,
                SimpleDateFormat(
                    Constants.FILE_NAME_FORMAT + Random.nextInt(), Locale.getDefault()
                ).format(System.currentTimeMillis()) + ".jpg")

            val outputOption = ImageCapture
                .OutputFileOptions
                .Builder(photoFile)
                .build()

            imageCapture.takePicture(
                outputOption, ContextCompat.getMainExecutor(requireActivity()),
                object : ImageCapture.OnImageSavedCallback {

                    override fun onImageSaved(outputFileResults: ImageCapture.OutputFileResults) {
                        val savedUri = Uri.fromFile(photoFile)

                        takePhotos.add(ImageModel(imageUri = savedUri, imageName = photoFile.name))
                        sharedViewModel.tempImageSelected(takePhotos)

                        Toast.makeText(context, "Berhasil Mengambil Foto", Toast.LENGTH_SHORT).show()
                        handleAdapter(takePhotos)
                        Loader.progressDialog?.dismiss()
                    }

                    override fun onError(exception: ImageCaptureException) {
                        Toast.makeText(context, "Gagal mengambil foto", Toast.LENGTH_SHORT).show()
                        Loader.progressDialog?.dismiss()
                    }

                }
            )
        }else{
            println("MAKSIMALLLLLL $takePhotos")
            Toast.makeText(context, "Maksimal hanya 9 foto.", Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleAdapter(data: ArrayList<ImageModel>) {
        photoListAdapter = PhotoListAdapter(data, this)
        binding.rvListPhoto.apply {
            adapter = photoListAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        }
    }

    private fun checkPermissions(): Boolean {
       return Constants.REQUIRED_PERMISSIONS.all {
           ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
       }
    }

    override fun onDestroy() {
        super.onDestroy()

        cameraExecutor.shutdown()
    }

    override fun deletePhoto(result: ImageModel) {
        sharedViewModel.tempImageSelected(takePhotos)
    }



}