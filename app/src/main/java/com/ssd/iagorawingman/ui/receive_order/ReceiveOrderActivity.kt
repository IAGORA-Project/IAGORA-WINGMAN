package com.ssd.iagorawingman.ui.receive_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.databinding.ActivityReceiveOrderBinding
import com.ssd.iagorawingman.ui.process_order.ProcessOrderActivity
import com.ssd.iagorawingman.utils.Loader
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiveOrderBinding
    private val receiveOrderViewModel: ReceiveOrderViewModel by viewModel()
    private lateinit var receiveOrderProductAdapter: ReceiveOrderProductAdapter
    private var dataNotif: String = ""
    private var receiveOrderBody: ReceiveOrderBody? = null

    private fun initBundle() {
        dataNotif = intent.getStringExtra("data-notif").toString()
        receiveOrderBody = Gson().fromJson(dataNotif, ReceiveOrderBody::class.java)
        receiveOrderBody?.let{ data -> handleViewAction(data) }
        receiveOrderBody?.listProduct?.let { listProduct -> handleAdapterListProduct(listProduct) }
        Log.d("dataNotifdataNotif", receiveOrderBody.toString())
        Log.d("dfgbdsgsdgsdgdsgsd", dataNotif)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()
        Loader.handleLoading(this)
    }

    private fun handleViewAction(data: ReceiveOrderBody) {
        binding.tvUserName.text = data.dataUser?.fullName
        Glide
            .with(this)
            .load(data.dataUser?.imgProfile)
            .into(binding.ivPicUser)


        binding.incBottom.btnAccepted.setOnClickListener {
            receiverOrderAccepted(data)
        }

        binding.incBottom.btnCancel.setOnClickListener {
            receiverOrderCancelled(data)
        }
    }

    private fun handleAdapterListProduct(data: ArrayList<ReceiveOrderBody.Product>) {
        receiveOrderProductAdapter = ReceiveOrderProductAdapter(data)
        binding.rvListProduct.apply {
            adapter = receiveOrderProductAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    private fun receiverOrderAccepted(receiveOrderBody: ReceiveOrderBody) {
        receiveOrderViewModel.vmAcceptedReceiverOrder(receiveOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        println("KJDBHJDHDJHDD ${res.message}")
                        Loader.progressDialog?.show()
                    }
                    Status.SUCCESS -> {
                        println("KJDBHJDHDJHDD ${res.data}")
                        Loader.progressDialog?.dismiss()
                        ProcessOrderActivity.newInstance(this, 0)
                        finish()
                    }
                    Status.ERROR -> {
                        Loader.progressDialog?.dismiss()
                    }
                }
            }
        })
    }


    private fun receiverOrderCancelled(receiveOrderBody: ReceiveOrderBody){
        receiveOrderViewModel.vmCancelledReceiverOrder(receiveOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        println("KJDBHJDHDJHDD ${res.message}")
                        Loader.progressDialog?.show()
                    }
                    Status.SUCCESS -> {
                        println("KJDBHJDHDJHDD ${res.data}")
                        Loader.progressDialog?.dismiss()
                        ProcessOrderActivity.newInstance(this, 0)
                        finish()
                    }
                    Status.ERROR -> {
                        Loader.progressDialog?.dismiss()
                    }
                }
            }
        })
    }
}