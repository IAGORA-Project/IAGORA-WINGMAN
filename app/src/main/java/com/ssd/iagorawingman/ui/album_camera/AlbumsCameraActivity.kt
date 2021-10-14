package com.ssd.iagorawingman.ui.album_camera

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.FileUtils
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.data.source.local.model.ImageTakeCamera
import com.ssd.iagorawingman.databinding.ActivityAlbumCameraBinding
import com.ssd.iagorawingman.ui.album_camera.albums.AlbumsFragment
import com.ssd.iagorawingman.ui.album_camera.camera.CameraFragment
import com.ssd.iagorawingman.ui.process_order.ProcessOrderTabLayoutAdapter

import org.koin.androidx.viewmodel.ext.android.viewModel


import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.utils.Status
import okhttp3.MediaType
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStream
import android.provider.MediaStore
import android.util.Log
import okhttp3.MultipartBody

import okhttp3.RequestBody
import java.io.ByteArrayOutputStream
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.RequestBody.Companion.asRequestBody
import android.os.Environment
import okhttp3.RequestBody.Companion.toRequestBody
import android.provider.MediaStore.MediaColumns





class AlbumsCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumCameraBinding
    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()
    private val sharedViewModel: SharedViewModel by viewModel()
    private var tempImageSelected: ArrayList<Image>? = null
    private var tempImageSelected2: ArrayList<ImageTakeCamera>? = null
    private val pasarViewModel: PasarViewModel by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImageSelected()
        getImageSelected2()
        handleView()
        handleTabViewPager()
    }

    private fun getImageSelected() {
        sharedViewModel.imageSelected.observe(this, {data ->
            tempImageSelected = data
        })
    }

    private fun getImageSelected2() {
        sharedViewModel.imageSelected2.observe(this, {data ->
            tempImageSelected2 = data
        })
    }

    fun getImageResized(context: Context, selectedImage: Uri): Bitmap {
        var bm: Bitmap? = null
        val sampleSizes = intArrayOf(10, 8, 5, 3, 2, 1)
        var i = 0
        val minWidthQuality = 500
        do {
            bm = decodeBitmap(context, selectedImage, sampleSizes[i])
            i++
        } while (bm!!.width < minWidthQuality && i < sampleSizes.size)
        return bm
    }

    fun decodeBitmap(context: Context, theUri: Uri, sampleSize: Int? = null): Bitmap? {
        var inputStream = context.contentResolver.openInputStream(theUri)
        val options = BitmapFactory.Options()
        options.inJustDecodeBounds = true
        BitmapFactory.decodeStream(inputStream, null, options)
        if(null != sampleSize) options.inSampleSize = sampleSize
        inputStream!!.close()
        inputStream = context.contentResolver.openInputStream(theUri)
        options.inJustDecodeBounds = false
        return BitmapFactory.decodeStream(inputStream, null, options)
    }

    fun Context.persistImage(bitmap: Bitmap, name: String): File {
        val filesDir: File = this.filesDir
        val imageFile = File(filesDir, "$name.jpg")
        var os: OutputStream? = null
        try {
            os = FileOutputStream(imageFile)
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, os)
            os.flush()
            os.close()
        } catch (e: Exception) {
        }
        return imageFile
    }



    private fun handleView(){
        binding.incHeader.tvNext.text = "Lanjut"
        binding.incHeader.ivBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.incHeader.tvNext.setOnClickListener {

            val multiPart: ArrayList<MultipartBody.Part> = ArrayList()
//            tempImageSelected2?.forEach {
//                val bitmap = getImageResized(this, it.uri!!)
//                val files = File(it.uri.toString())
//                val newFile =  this.persistImage(bitmap, files.name)
//                val mFile: RequestBody = newFile.asRequestBody("image/*".toMediaTypeOrNull())
//                multiPart.add(MultipartBody.Part.createFormData("img", it.imageName, mFile))
//            }

            tempImageSelected?.forEach {
//                val bitmap = getImageResized(this, it.uri!!)
//                val files = File(it.uri.toString())
//                val newFile =  this.persistImage(bitmap, files.name)
//                val mFile: RequestBody = newFile.asRequestBody("image/*".toMediaTypeOrNull())


                val sourceUri = Uri.fromFile(File(it.imagePath))
                println("jerfh839h $sourceUri")



                val files = File((sourceUri.path), it.imageName)
                val mFile: RequestBody =  files.path.toRequestBody("image/*".toMediaTypeOrNull())


                multiPart.add(MultipartBody.Part.createFormData("img", files.name, mFile))
            }


            pasarViewModel.vmAddPhoto(multiPart).observe(this, {
                it.getContentIfNotHandled().let { res ->
                    when(res?.status) {
                        Status.SUCCESS -> {
                            println("YEESSSHHHHHHHHHHH")
                        }
                    }
                }
            })

        }
    }

    private fun handleTabViewPager() {
        //disable swipe view pager
        binding.vpTabs.isUserInputEnabled = false

        listFragment.add(AlbumsFragment())
        listFragment.add(CameraFragment())

        titleTabLayout.add("Album")
        titleTabLayout.add("Kamera")

        val sectionsPagerAdapter = ProcessOrderTabLayoutAdapter(this, listFragment)
        binding.vpTabs.adapter = sectionsPagerAdapter
        TabLayoutMediator(binding.tabs, binding.vpTabs) { tab, position ->
            tab.text = titleTabLayout[position]
        }.attach()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        println("SOJSIOSJS ${permissions}")
    }
}