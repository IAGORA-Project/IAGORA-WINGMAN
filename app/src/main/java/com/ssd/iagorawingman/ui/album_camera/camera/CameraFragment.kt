package com.ssd.iagorawingman.ui.album_camera.camera


import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.camera.lifecycle.ProcessCameraProvider
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentCameraBinding
import com.ssd.iagorawingman.utils.Constants
import android.net.Uri
import androidx.camera.core.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagorawingman.data.source.local.model.ImageTakeCamera
import com.ssd.iagorawingman.utils.Loader
import com.ssd.iagorawingman.utils.Permission
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors
import kotlin.random.Random
import android.os.FileUtils
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import okhttp3.RequestBody
import org.koin.androidx.viewmodel.ext.android.viewModel


class CameraFragment : Fragment() {

    private lateinit var binding: FragmentCameraBinding
    private var imageCapture: ImageCapture? = null
    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService
    private var takePhotos: ArrayList<ImageTakeCamera> = ArrayList()
    private lateinit var photoListAdapter: PhotoListAdapter
    private val sharedViewModel: SharedViewModel by viewModel()


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        cameraExecutor = Executors.newSingleThreadExecutor()
        outputDirectory = getOutputDirectory()
        binding =  FragmentCameraBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


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

                    println("PSOPSOPSOS $savedUri")


                    takePhotos.add(ImageTakeCamera(imagePath = photoFile.path, imageName = photoFile.name,
                        uri = savedUri
                    ))

                    sharedViewModel.SharedImageSelected2(takePhotos)


                    Loader.progressDialog?.dismiss()
                    Toast.makeText(context, "Berhasil Mengambil Foto", Toast.LENGTH_SHORT).show()



                    photoListAdapter = PhotoListAdapter(takePhotos)
                    binding.rvListPhoto.apply {
                        adapter = photoListAdapter
                        layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
                    }
                }

                override fun onError(exception: ImageCaptureException) {
                    Toast.makeText(context, "Gagal mengambil foto", Toast.LENGTH_SHORT).show()
                    Loader.progressDialog?.dismiss()

                }

            }
        )
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
}