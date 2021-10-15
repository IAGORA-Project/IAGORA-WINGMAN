package com.ssd.iagorawingman.ui.pasar.add_product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.databinding.ActivityAddProductBinding
import com.ssd.iagorawingman.ui.album_camera.AlbumsCameraActivity
import com.ssd.iagorawingman.ui.album_camera.camera.PhotoListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var photoListProductAdapter: PhotoListProductAdapter
    private val sharedViewModel: SharedViewModel by viewModel()
    private var imageProductSelected: ArrayList<Image> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getImageSelected()
        handleView()
    }


    private fun getImageSelected() {
        sharedViewModel.addProductImage.observe(this, { data ->
            if(!data.isNullOrEmpty()) {
                imageProductSelected = data
                handleAdapter(data)

                println("POPWOIOWHJWHW $data")
            }
        })
    }


    private fun handleView() {
        binding.incHeader.tvTitle.text = "Tambah Produk"
        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }

        binding.incAddImages.tvTitle.text = "Foto Produk"
        binding.incAddImages.shapeableImageView.setOnClickListener {
            if(imageProductSelected.size < 9){
                AlbumsCameraActivity.newInstance(this, imageProductSelected)
            }else{
                Toast.makeText(this, "Maksimal hanya 9 foto.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvTitleNamaProduk.text = "Nama Produk"
    }


    private fun handleAdapter(data: ArrayList<Image>) {
        photoListProductAdapter = PhotoListProductAdapter(data)
        binding.rvListPhotoProduct.apply {
            adapter = photoListProductAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        }
        binding.rvListPhotoProduct.visibility = View.VISIBLE
    }


    override fun onDestroy() {
        super.onDestroy()

        sharedViewModel.AddProductImage(null)
    }
}