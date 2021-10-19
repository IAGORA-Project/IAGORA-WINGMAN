package com.ssd.iagorawingman.ui.pasar.list_product_pasar

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagorawingman.data.source.remote.response.ResGetListProductPasar
import com.ssd.iagorawingman.databinding.ActivityListProductPasarBinding
import com.ssd.iagorawingman.ui.chat.ChatActivity
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.ui.pasar.add_product.AddProductActivity
import com.ssd.iagorawingman.ui.pasar.list_pasar.ListPasarAdapter
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListProductPasarActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListProductPasarBinding
    private lateinit var listProductPasarAdapter: ListProductPasarAdapter
    private val pasarViewModel: PasarViewModel by viewModel()
    private var idPasar: String? = null


    companion object {
        fun newInstance(context: Context, id_pasar: String) {
            val dataIntent = Intent(context, ListProductPasarActivity::class.java)
            dataIntent.putExtra("id-product", id_pasar)
            context.startActivity(dataIntent)
        }
    }

    private fun initBundle() {
        idPasar = intent.getStringExtra("id-product")
        Log.d("idPasar", idPasar.toString())
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListProductPasarBinding.inflate(layoutInflater)
        setContentView(binding.root)


        initBundle()
        handleView()
        getProductPasar()
    }

    private fun handleView() {
        binding.incHeader.tvTitle.text = "Produk Pasar"
        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }

        binding.btnAddproduct.text = "Tambah Produk"
        binding.btnAddproduct.setOnClickListener {
            idPasar?.let { it1 -> AddProductActivity.newInstance(this, it1) }
        }
    }


    private fun getProductPasar() {
        idPasar?.let {id ->
            pasarViewModel.vmGetListProductPasar(id).observe(this, {
                it.getContentIfNotHandled().let { res ->
                    when(res?.status){
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            res.data?.success?.let { it1 -> handleAdapter(it1) }
                        }
                        Status.ERROR -> {

                        }
                    }
                }
            })
        }
    }


    private fun handleAdapter(data: ArrayList<ResGetListProductPasar.Success>) {
        listProductPasarAdapter = ListProductPasarAdapter(data)
        binding.rvListProduct.apply {
            adapter = listProductPasarAdapter
        }
    }
}