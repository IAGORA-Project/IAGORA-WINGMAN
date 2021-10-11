package com.ssd.iagorawingman.ui.pasar.list_pasar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.ssd.iagorawingman.data.source.remote.response.ResGetListPasar
import com.ssd.iagorawingman.databinding.ActivityListPasarBinding
import com.ssd.iagorawingman.ui.pasar.PasarViewModel
import com.ssd.iagorawingman.ui.pasar.list_product_pasar.ListProductPasarActivity
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ListPasarActivity : AppCompatActivity(), ListPasarAdapter.ItemCallBackAdapter {

    private lateinit var binding: ActivityListPasarBinding
    private lateinit var listPasarAdapter: ListPasarAdapter
    private val pasarViewModel: PasarViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListPasarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        handleView()
        getListPasar()
    }

    private fun handleView() {
        binding.incHeader.tvTitle.text = "Pasar"
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