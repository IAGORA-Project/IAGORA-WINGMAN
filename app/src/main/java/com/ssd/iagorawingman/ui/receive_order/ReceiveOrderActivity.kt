package com.ssd.iagorawingman.ui.receive_order

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.gson.Gson
import com.ssd.iagorawingman.data.source.remote.body.AcceptedOrCancelledOrderBody
import com.ssd.iagorawingman.databinding.ActivityReceiveOrderBinding
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveOrderActivity : AppCompatActivity() {

    private lateinit var binding: ActivityReceiveOrderBinding
    private val receiveOrderViewModel: ReceiveOrderViewModel by viewModel()
    private var dataNotif: String = ""
    private var acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody? = null

    private fun initBundle() {
        dataNotif = intent.getStringExtra("data-notif").toString()
        acceptedOrCancelledOrderBody = Gson().fromJson(dataNotif, AcceptedOrCancelledOrderBody::class.java)
        Log.d("dataNotifdataNotif", acceptedOrCancelledOrderBody.toString())
        Log.d("dfgbdsgsdgsdgdsgsd", dataNotif)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityReceiveOrderBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initBundle()
        handleViewAction()
    }

    private fun handleViewAction() {
        binding.incBottom.btnAccepted.setOnClickListener {
            acceptedOrCancelledOrderBody?.let { it1 -> receiverOrderAccepted(it1) }
        }

        binding.incBottom.btnCancel.setOnClickListener {
            acceptedOrCancelledOrderBody?.let { it1 -> receiverOrderCancelled(it1) }
        }
    }

    private fun receiverOrderAccepted(acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody) {
        receiveOrderViewModel.vmAcceptedReceiverOrder(acceptedOrCancelledOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        println("KJDBHJDHDJHDD ${res.message}")
                    }
                    Status.SUCCESS -> {
                        println("KJDBHJDHDJHDD ${res.data}")
//                        ProcessOrderFragment.newInstance(this, 0)
                        finish()
                    }
                }
            }
        })
    }


    private fun receiverOrderCancelled(acceptedOrCancelledOrderBody: AcceptedOrCancelledOrderBody){
        receiveOrderViewModel.vmCancelledReceiverOrder(acceptedOrCancelledOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                when(res?.status){
                    Status.LOADING -> {
                        println("KJDBHJDHDJHDD ${res.message}")
                    }
                    Status.SUCCESS -> {
                        println("KJDBHJDHDJHDD ${res.data}")
//                        ProcessOrderFragment.newInstance(this, 0)
                        finish()
                    }
                }
            }
        })
    }
}