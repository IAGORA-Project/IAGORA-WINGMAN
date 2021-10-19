package com.ssd.iagorawingman.ui.album_camera


import android.content.Context
import android.content.Intent
import android.os.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.ImageModel
import com.ssd.iagorawingman.databinding.ActivityAlbumCameraBinding
import com.ssd.iagorawingman.ui.album_camera.albums.AlbumsFragment
import com.ssd.iagorawingman.ui.album_camera.camera.CameraFragment
import com.ssd.iagorawingman.ui.process_order.ProcessOrderTabLayoutAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import okhttp3.MultipartBody
import android.view.View
import android.widget.Toast
import com.ssd.iagorawingman.utils.Loader

class AlbumsCameraActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAlbumCameraBinding
    private val titleTabLayout = mutableListOf<String>()
    private val listFragment = mutableListOf<Fragment>()
    private val sharedViewModel: SharedViewModel by viewModel()
    private var tempImageModelSelected: ArrayList<ImageModel>? = null
    private var imageModelProductSelected: ArrayList<ImageModel> = ArrayList()
    val multiPart: ArrayList<MultipartBody.Part> = ArrayList()



    companion object {
        fun newInstance(context: Context, data: ArrayList<ImageModel>) {
            val dataIntent = Intent(context, AlbumsCameraActivity::class.java)
            dataIntent.putExtra("image-product", data)
            context.startActivity(dataIntent)
        }
    }

    private fun initBundle() {
        imageModelProductSelected = intent.getParcelableArrayListExtra("image-product")!!
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAlbumCameraBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()
        Loader.handleLoading(this)
        getImageSelected()
        handleView()
        handleTabViewPager()
    }


    private fun getImageSelected() {
        sharedViewModel.imageModelSelected.observe(this, { data ->
            if (!data.isNullOrEmpty()){
                tempImageModelSelected = data
            }

            if(data.isNullOrEmpty()) {
                binding.tabs.visibility = View.VISIBLE
            }else{
                binding.tabs.visibility = View.GONE
            }
        })
    }


    private fun handleView(){
        binding.incHeader.tvNext.text = "Lanjut"
        binding.incHeader.ivBackButton.setOnClickListener {
            onBackPressed()
        }

        binding.incHeader.tvNext.setOnClickListener {
            if(!tempImageModelSelected.isNullOrEmpty()){
                if(imageModelProductSelected.size + tempImageModelSelected!!.size <= 9) {
                    Loader.progressDialog?.show()
                    Handler(Looper.getMainLooper()).postDelayed({
                        tempImageModelSelected?.forEach {
//                        val bitmap = getImageResized(this, it.imageUri!!)
//                        val newFile =  this.persistImage(bitmap, it.imageName!!)
//                        val mFile: RequestBody =  newFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())
//                        multiPart.add(MultipartBody.Part.createFormData("img", it.imageName, mFile))

                            imageModelProductSelected.add(
                                ImageModel(
                                    imageUri = it.imageUri,
                                    imageName = it.imageName,
                                )
                            )
                            sharedViewModel.AddProductImage(imageModelProductSelected)

                            Loader.progressDialog?.dismiss()
                            onBackPressed()
                        }
                    }, 500)
                }else{
                    Toast.makeText(this, "Maksimal hanya 9 foto. Anda hanya bisa menambah ${9 - imageModelProductSelected.size} lagi.", Toast.LENGTH_SHORT).show()
                }



//                Handler(Looper.getMainLooper()).postDelayed({
//                    pasarViewModel.vmAddPhoto(multiPart).observe(this, {
//                        it.getContentIfNotHandled().let { res ->
//                            when(res?.status) {
//                                Status.SUCCESS -> {
//                                    Loader.progressDialog?.dismiss()
//                                    Toast.makeText(this, "sdjshj", Toast.LENGTH_SHORT).show()
//                                    finish()
//                                }
//                                Status.ERROR -> {
//                                    Loader.progressDialog?.dismiss()
//                                }
//                            }
//                        }
//                    })
//                }, 1000)
            }
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


    override fun onDestroy() {
        super.onDestroy()

        sharedViewModel.TempImageSelected(null)
    }

}