package com.ssd.iagorawingman.ui.pasar.add_product

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowManager
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagora_user.data.source.local.shared_view_model.SharedViewModel
import com.ssd.iagorawingman.data.source.local.model.ImageModel
import com.ssd.iagorawingman.databinding.ActivityAddProductBinding
import com.ssd.iagorawingman.ui.album_camera.AlbumsCameraActivity
import org.koin.androidx.viewmodel.ext.android.viewModel
import androidx.core.widget.addTextChangedListener
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.local.model.ListVariantAddProductModel
import com.ssd.iagorawingman.data.source.remote.body.AddProductBody
import com.ssd.iagorawingman.data.source.remote.response.ResGetListTypeAndCategory
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.utils.Loader
import com.ssd.iagorawingman.utils.Status
import com.ssd.iagorawingman.utils.getImageResized
import com.ssd.iagorawingman.utils.persistImage
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.lang.Exception


class AddProductActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddProductBinding
    private lateinit var photoListProductAdapter: PhotoListProductAdapter
    private lateinit var variantPriceAdapter: VariantPriceAdapter
    private val sharedViewModel: SharedViewModel by viewModel()
    private val pasarViewModel: PasarViewModel by viewModel()
    private var idPasar: String? = null
    private var imageModelProductSelected: ArrayList<ImageModel> = ArrayList()
    var listKategoriString: ArrayList<String> = ArrayList()
    var listTypeString: ArrayList<String> = ArrayList()
    var listKategori: ArrayList<ResGetListTypeAndCategory.Success.Category> = ArrayList()
    var listType: ArrayList<ResGetListTypeAndCategory.Success.Type> = ArrayList()
    var incrementVarianPrice = 1
    var listVariantPrice: ArrayList<ListVariantAddProductModel.Variant> = ArrayList()
    var addProductBody: AddProductBody = AddProductBody()


    companion object {
        fun newInstance(context: Context, id_pasar: String) {
            val dataIntent = Intent(context, AddProductActivity::class.java)
            dataIntent.putExtra("id-product", id_pasar)
            context.startActivity(dataIntent)
        }
    }

    private fun initBundle() {
        idPasar = intent.getStringExtra("id-product")
        addProductBody.idPasar = idPasar
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddProductBinding.inflate(layoutInflater)
        window.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN) // untuk supaya button tidak naik ketika keyboard aktif
        setContentView(binding.root)

        binding.btnAddProduct.btnPrimary.isEnabled = true
        listVariantPrice.add(ListVariantAddProductModel.Variant(idList = incrementVarianPrice))
        initBundle()
        getListTypeAndCategory()
        getImageSelected()
        handleView()
        handleAdapterVariantPrice(listVariantPrice, "")
    }


    private fun getImageSelected() {
        sharedViewModel.addProductImageModel.observe(this, { data ->
            if(!data.isNullOrEmpty()) {
                imageModelProductSelected = data
                handleAdapterPhotoProduct(data)
            }
        })
    }


    private fun handleView() {
        binding.incHeader.tvTitle.text = "Tambah Produk"
        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }

        binding.incAddImages.tvTitle.text = "Foto Produk"
        binding.incAddImages.shapeableImageView.setOnClickListener {
            if(imageModelProductSelected.size < 9){
                AlbumsCameraActivity.newInstance(this, imageModelProductSelected)
            }else{
                Toast.makeText(this, "Maksimal hanya 9 foto.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvTitleNamaProduk.text = "Nama Produk"
        binding.tilProductName.editText?.addTextChangedListener {
            addProductBody.namaProduk = it.toString()
            checkedEnableButton()
        }


        //Drop Down Kategori Produk
        binding.tvTitleKategoriProduct.text = "Kategori Produk"
        val arrayAdapter = ArrayAdapter(this, R.layout.item_dropdown_text, listKategoriString)
        binding.dropdownChildKategoriProduct.setAdapter(arrayAdapter)
        binding.dropdownChildKategoriProduct.setOnItemClickListener { parent, view, position, id ->
            listKategori.forEach {
                if(it.categoryName == listKategoriString[position]){
                    println("positionpositionKATEG ${it}")
                    addProductBody.itemCategories = it.idCategories
                    checkedEnableButton()
                }
            }
        }


        //Drop Down Type Produk
        binding.tvTitleTypeProduct.text = "Tipe Produk"
        val arrayAdapterTypeProduct = ArrayAdapter(this, R.layout.item_dropdown_text, listTypeString)
        binding.dropdownChildTypeProduct.setAdapter(arrayAdapterTypeProduct)
        binding.dropdownChildTypeProduct.setOnItemClickListener { parent, view, position, id ->
            listType.forEach {
                if(it.typeName == listTypeString[position]){
                    println("positionpositionTYPE ${it}")
                    addProductBody.itemType = it.idType
                    checkedEnableButton()
                }
            }
        }


        //Drop Down Satuan Produk
        binding.tvTitleSatuan.text = "Satuan Produk"
        val listSatuan = resources.getStringArray(R.array.satuan)
        val arrayAdapterSatuanProduct = ArrayAdapter(this, R.layout.item_dropdown_text, listSatuan)
        binding.dropdownChildSatuan.setAdapter(arrayAdapterSatuanProduct)
        binding.dropdownChildSatuan.setOnItemClickListener { parent, view, position, id ->
            checkedEnableButton()
            addProductBody.satuan = listSatuan[position]
            listVariantPrice = ArrayList()
            listVariantPrice.add(ListVariantAddProductModel.Variant(idList = incrementVarianPrice))
            handleAdapterVariantPrice(listVariantPrice, listSatuan[position])

            if(listSatuan[position] == "Pcs"){
                binding.tvAddVariantPrice.visibility = View.GONE
            }else {
                binding.tvAddVariantPrice.visibility = View.VISIBLE
            }
        }


        //Variant Price
        binding.tvTitleHarga.text = "Variant Harga"
        binding.tvAddVariantPrice.text = "+ Tambah varian harga"
        binding.tvAddVariantPrice.setOnClickListener {
            incrementVarianPrice++
            listVariantPrice.add(ListVariantAddProductModel.Variant(idList = incrementVarianPrice))
            addProductBody.satuan?.let { satuan -> handleAdapterVariantPrice(listVariantPrice, satuan) }
        }


        //Description Product
        binding.tvTitleDescriptionProduct.text = "Deskripsi Produk"
        binding.tilDescriptionProduct.editText?.addTextChangedListener {
            addProductBody.desc = it.toString()
            checkedEnableButton()
        }

        //Button Add Product
        binding.btnAddProduct.btnPrimary.text = "Tambahkan Produk"
        binding.btnAddProduct.btnPrimary.setOnClickListener {
            addProduct()
        }
    }


    private fun checkedEnableButton() {
//        binding.btnAddProduct.btnPrimary.isEnabled =
//            !imageModelProductSelected.isNullOrEmpty() &&
//            !addProductBody.namaProduk.isNullOrEmpty() &&
//            !addProductBody.itemCategories.isNullOrEmpty() &&
//            !addProductBody.itemType.isNullOrEmpty() &&
//            !addProductBody.desc.isNullOrEmpty()
    }


    private fun getListTypeAndCategory() {
        pasarViewModel.vmGetListTypeAndCategory().observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status) {
                    Status.LOADING -> {
                        binding.ProgressBar.visibility = View.VISIBLE
                        binding.nestedScrollView.visibility = View.GONE
                        binding.btnAddProduct.root.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        binding.ProgressBar.visibility = View.GONE
                        binding.nestedScrollView.visibility = View.VISIBLE
                        binding.btnAddProduct.root.visibility = View.VISIBLE

                        if(!res.data?.success?.categories.isNullOrEmpty()){
                            listKategori = res.data?.success?.categories!!
                            res.data.success?.categories?.forEach { data ->
                                data.categoryName?.let { it1 -> listKategoriString.add(it1) }
                            }
                        }

                        if(!res.data?.success?.type.isNullOrEmpty()){
                            listType = res.data?.success?.type!!
                            res.data.success?.type?.forEach { data ->
                                data.typeName?.let { it1 -> listTypeString.add(it1) }
                            }
                        }

                    }
                    Status.ERROR -> {
                        binding.ProgressBar.visibility = View.GONE
                        binding.nestedScrollView.visibility = View.GONE
                        binding.btnAddProduct.root.visibility = View.GONE
                    }
                }
            }
        })
    }


    private fun addProduct() {
        try {
            Loader.progressDialog?.show()
            addProductBody.variant = ArrayList()
            addProductBody.image = ArrayList()

            Handler(Looper.getMainLooper()).postDelayed({
                listVariantPrice.forEach {listVariant ->
                    if(!listVariant.price.isNullOrEmpty()){
                        if(listVariant.variant.isNullOrEmpty() && addProductBody.satuan == "Pcs"){
                            addProductBody.variant.add(
                                AddProductBody.Variant(
                                    avgPrice = listVariant.price!!.toInt(),
                                    highestPrice = 0,
                                    lowPrice = 0,
                                    uom = listVariant.variant?.toInt(),
                                    satuan = listVariant.satuan
                                )
                            )
                        }else if(!listVariant.variant.isNullOrEmpty() && addProductBody.satuan == "Gram"){
                            addProductBody.variant.add(
                                AddProductBody.Variant(
                                    avgPrice = listVariant.price!!.toInt(),
                                    highestPrice = 0,
                                    lowPrice = 0,
                                    uom = listVariant.variant?.toInt(),
                                    satuan = listVariant.satuan
                                )
                            )
                        }
                    }
                }

                imageModelProductSelected.forEach { img ->
                    val bitmap = getImageResized(this, img.imageUri!!)
                    val newFile =  this.persistImage(bitmap, img.imageName!!)
                    val mFile: RequestBody =  newFile.asRequestBody("multipart/form-data".toMediaTypeOrNull())

                    addProductBody.image.add(MultipartBody.Part.createFormData("img", img.imageName, mFile))
                }
            }, 500)


            Handler(Looper.getMainLooper()).postDelayed({
                println("CEKKIRIMDATA $addProductBody")

                pasarViewModel.vmAddProduct(addProductBody).observe(this, {
                    it.getContentIfNotHandled().let { res ->
                        when(res?.status){
                            Status.LOADING -> {
                                println("loaddinhghdsff ${res.message}")
                            }
                            Status.SUCCESS -> {
                                Loader.progressDialog?.dismiss()
                                Toast.makeText(this, "Berhasil mengajukan penambahkan produk.", Toast.LENGTH_SHORT).show()
                                finish()
                            }
                            Status.ERROR -> {
                                println("ERRORACTIVITY ${res.message}")
                                Loader.progressDialog?.dismiss()
                            }
                        }
                    }
                })
            }, 1000)

        }catch (e: Exception){ }
    }


    private fun handleAdapterPhotoProduct(data: ArrayList<ImageModel>) {
        photoListProductAdapter = PhotoListProductAdapter(data)
        binding.rvListPhotoProduct.apply {
            adapter = photoListProductAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL ,false)
        }
        binding.rvListPhotoProduct.visibility = View.VISIBLE
    }


    private fun handleAdapterVariantPrice(data: ArrayList<ListVariantAddProductModel.Variant>, satuan: String) {
        variantPriceAdapter = VariantPriceAdapter(data, satuan)
        binding.rvListVariantPrice.apply {
            adapter = variantPriceAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onResume() {
        super.onResume()
        Loader.handleLoading(this)
    }


    override fun onDestroy() {
        super.onDestroy()
        sharedViewModel.AddProductImage(null)
    }

}