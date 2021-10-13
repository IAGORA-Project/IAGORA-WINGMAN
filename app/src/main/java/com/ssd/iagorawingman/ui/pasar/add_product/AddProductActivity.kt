package com.ssd.iagorawingman.ui.pasar.add_product

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ssd.iagorawingman.databinding.ActivityAddProductBinding
import com.ssd.iagorawingman.ui.album_camera.AlbumsCameraActivity

class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleView()
    }

    private fun handleView() {
        binding.incHeader.tvTitle.text = "Tambah Produk"
        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }

        binding.incAddImages.shapeableImageView.setOnClickListener {
            startActivity(Intent(this, AlbumsCameraActivity::class.java))
        }
    }
}