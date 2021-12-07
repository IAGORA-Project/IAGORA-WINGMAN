package com.iagora.wingman.app.ui.pasar.list_pasar

import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.iagora.wingman.app.databinding.ActivityListPasarBinding
import com.iagora.wingman.app.ui.pasar.PasarViewModel
import com.iagora.wingman.app.ui.pasar.list_product_pasar.ListProductPasarActivity
import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.core.source.remote.response.ResGetListPasar
import com.iagora.wingman.helper.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPasarActivity :
    BaseActivity<ActivityListPasarBinding>({ ActivityListPasarBinding.inflate(it) }),
    ListPasarAdapter.ItemCallBackAdapter {

    private lateinit var listPasarAdapter: ListPasarAdapter
    private val pasarViewModel: PasarViewModel by viewModel()

    override fun setView() {
        handleView()
        getListPasar()
    }

    private fun handleView() {
        binding.incHeader.tvTitle.text = StringBuilder("Pasar")
        binding.incHeader.ivBackButton.setOnClickListener { onBackPressed() }
    }


    private fun getListPasar() {
        pasarViewModel.vmGetListPasar().observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        binding.ProgressBar.visibility = View.VISIBLE
                        binding.rvListPasar.visibility = View.GONE
                    }
                    Status.SUCCESS -> {
                        println("SUKKEESSSSSSS ${res.data}")
                        binding.ProgressBar.visibility = View.GONE
                        binding.rvListPasar.visibility = View.VISIBLE

                        res.data?.success?.let { data -> handleAdapter(data) }
                    }
                }
            }
        })
    }


    private fun handleAdapter(data: ArrayList<ResGetListPasar.Success>) {
        listPasarAdapter = ListPasarAdapter(data, this)
        binding.rvListPasar.apply {
            adapter = listPasarAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onClickPasar(result: ResGetListPasar.Success) {
        result.idPasar?.let { ListProductPasarActivity.newInstance(this, it) }
    }
}