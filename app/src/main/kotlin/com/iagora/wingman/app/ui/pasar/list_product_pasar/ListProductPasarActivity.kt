//package com.iagora.wingman.app.ui.pasar.list_product_pasar
//
//import android.content.Context
//import android.content.Intent
//import android.util.Log
//import com.iagora.wingman.app.databinding.ActivityListProductPasarBinding
//import com.iagora.wingman.app.ui.pasar.PasarViewModel
//import com.iagora.wingman.app.ui.pasar.add_product.AddProductActivity
//import com.iagora.wingman.commons.ui.base.BaseActivity
//import com.iagora.wingman.market.helper.data.remote.response.ResGetListProductPasar
//import com.iagora.wingman.helper.Status
//import org.koin.androidx.viewmodel.ext.android.viewModel
//
//class ListProductPasarActivity :
//    BaseActivity<ActivityListProductPasarBinding>({ ActivityListProductPasarBinding.inflate(it) }) {
//
//
//    private lateinit var listProductPasarAdapter: ListProductPasarAdapter
//    private val pasarViewModel: PasarViewModel by viewModel()
//    private var idPasar: String? = null
//
//
//    companion object {
//        fun newInstance(context: Context, id_pasar: String) {
//            val dataIntent = Intent(context, ListProductPasarActivity::class.java)
//            dataIntent.putExtra("id-product", id_pasar)
//            context.startActivity(dataIntent)
//        }
//    }
//
//    private fun initBundle() {
//        idPasar = intent.getStringExtra("id-product")
//        Log.d("idPasar", idPasar.toString())
//    }
//
//    override fun setView() {
//        initBundle()
//        handleView()
//        getProductPasar()
//    }
//
//    private fun handleView() {
//        binding.incHeader.tvTitle.text = StringBuilder("Produk Pasar")
//        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }
//
//        binding.btnAddproduct.text = StringBuilder("Tambah Produk")
//        binding.btnAddproduct.setOnClickListener {
//            idPasar?.let { it1 -> AddProductActivity.newInstance(this, it1) }
//        }
//    }
//
//
//    private fun getProductPasar() {
//        idPasar?.let {id ->
//            pasarViewModel.vmGetListProductPasar(id).observe(this, {
//                it.getContentIfNotHandled().let { res ->
//                    when(res?.status){
//                        Status.LOADING -> {
//
//                        }
//                        Status.SUCCESS -> {
//                            res.data?.success?.let { it1 -> handleAdapter(it1) }
//                        }
//                        Status.ERROR -> {
//
//                        }
//                    }
//                }
//            })
//        }
//    }
//
//
//    private fun handleAdapter(data: ArrayList<com.iagora.wingman.market.helper.data.remote.response.ResGetListProductPasar.Success>) {
//        listProductPasarAdapter = ListProductPasarAdapter(data)
//        binding.rvListProduct.apply {
//            adapter = listProductPasarAdapter
//        }
//    }
//}