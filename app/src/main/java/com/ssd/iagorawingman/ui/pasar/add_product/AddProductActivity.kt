package com.ssd.iagorawingman.ui.pasar.add_product

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.Image
import com.ssd.iagorawingman.databinding.ActivityAddProductBinding
import com.ssd.iagorawingman.ui.album_camera.AlbumsCameraActivity
import com.ssd.iagorawingman.ui.album_camera.camera.PhotoListAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel
import android.widget.AutoCompleteTextView
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.local.model.ListVariantAddProductModel


class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var photoListProductAdapter: PhotoListProductAdapter
    private lateinit var variantPriceAdapter: VariantPriceAdapter
    private val sharedViewModel: SharedViewModel by viewModel()
    private var imageProductSelected: ArrayList<Image> = ArrayList()
    val listKategori: ArrayList<String> = ArrayList()
    var incrementVarianPrice = 1
    val listVariantPrice: ArrayList<ListVariantAddProductModel.Variant> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN) // untuk supaya button tidak naik ketika keyboard aktif
        setContentView(binding.root)

        listVariantPrice.add(ListVariantAddProductModel.Variant(idList = incrementVarianPrice))

        listKategori.add("Sayur")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")
        listKategori.add("Buah")


        getImageSelected()
        handleView()
        handleAdapterVariantPrice(listVariantPrice)

    }


    private fun getImageSelected() {
        sharedViewModel.addProductImage.observe(this, { data ->
            if(!data.isNullOrEmpty()) {
                imageProductSelected = data
                handleAdapterPhotoProduct(data)

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


        //Drop Down Kategori Produk
        binding.tvTitleKategoriProduct.text = "Kategori Produk"
        val arrayAdapter = ArrayAdapter(this, R.layout.item_dropdown_text, listKategori)
        binding.dropdownChildKategoriProduct.setAdapter(arrayAdapter)
        binding.dropdownChildKategoriProduct.setOnItemClickListener { parent, view, position, id ->
            println("positionposition ${listKategori.get(position)}")
        }


        //Drop Down Type Produk
        binding.tvTitleTypeProduct.text = "Tipe Produk"
        val arrayAdapterTypeProduct = ArrayAdapter(this, R.layout.item_dropdown_text, listKategori)
        binding.dropdownChildTypeProduct.setAdapter(arrayAdapterTypeProduct)
        binding.dropdownChildKategoriProduct.setOnItemClickListener { parent, view, position, id ->
            println("positionposition ${listKategori.get(position)}")
        }


        //Variant Price
        binding.tvTitleHarga.text = "Variant Harga"
        binding.tvAddVariantPrice.text = "+ Tambah varian harga"
        binding.tvAddVariantPrice.setOnClickListener {
            incrementVarianPrice++
            println("incrementVarianPrice $incrementVarianPrice")
            listVariantPrice.add(ListVariantAddProductModel.Variant(idList = incrementVarianPrice))
            handleAdapterVariantPrice(listVariantPrice)
        }


        //Button Add Product
        binding.btnAddProduct.btnPrimary.text = "Tambahkan Produk"
        binding.btnAddProduct.btnPrimary.setOnClickListener {
            println("CEKKKKKKK")
        }
    }


    private fun handleAdapterPhotoProduct(data: ArrayList<Image>) {
        photoListProductAdapter = PhotoListProductAdapter(data)
        binding.rvListPhotoProduct.apply {
            adapter = photoListProductAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        }
        binding.rvListPhotoProduct.visibility = View.VISIBLE
    }


    private fun handleAdapterVariantPrice(data: ArrayList<ListVariantAddProductModel.Variant>) {
        variantPriceAdapter = VariantPriceAdapter(data, this)
        binding.rvListVariantPrice.apply {
            adapter = variantPriceAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.AddProductImage(null)
    }
}