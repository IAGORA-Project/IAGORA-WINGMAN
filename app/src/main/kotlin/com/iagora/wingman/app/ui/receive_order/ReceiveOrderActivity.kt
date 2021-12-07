package com.iagora.wingman.app.ui.receive_order

import android.content.Intent
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.gson.Gson
import com.iagora.wingman.app.databinding.ActivityReceiveOrderBinding
import com.iagora.wingman.app.utils.Loader
import com.iagora.wingman.commons.ui.base.BaseActivity
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.helper.set
import com.iagora.wingman.process_order.features.main_features.ProcessOrderActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveOrderActivity :
    BaseActivity<ActivityReceiveOrderBinding>({ ActivityReceiveOrderBinding.inflate(it) }) {

    private val receiveOrderViewModel: ReceiveOrderViewModel by viewModel()
    private lateinit var receiveOrderProductAdapter: ReceiveOrderProductAdapter
    private var dataNotify: String = ""
    private var receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody? = null

    private fun initBundle() {
        dataNotify = intent.getStringExtra(KEY_DATA_NOTIFY).toString()
        receiveOrderBody = Gson().fromJson(dataNotify, com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody::class.java)

        receiveOrderBody?.let { data -> handleViewAction(data) }
        receiveOrderBody?.listProduct?.let { listProduct -> handleAdapterListProduct(listProduct) }
    }

    override fun setView() {
        initBundle()
        Loader.handleLoading(this)
    }

    private fun handleViewAction(data: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody) {

        binding.apply {
            with(data) {
                dataUser?.apply {
                    tvUserName.text = fullName
                    ivPicUser.loadPhotoProfile(imgProfile ?: "")
                }


                incBottom.apply {
                    btnAccepted.setOnClickListener {
                        receiverOrderAccepted(this@with)
                    }

                    btnCancel.setOnClickListener {
                        receiverOrderCancelled(this@with)
                    }
                }
            }
        }


    }

    private fun handleAdapterListProduct(data: ArrayList<com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody.Product>) {
        receiveOrderProductAdapter = ReceiveOrderProductAdapter(data)
        binding.rvListProduct.apply {
            adapter = receiveOrderProductAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onBackPressed() {
        receiveOrderBody?.let { data -> receiverOrderCancelled(data) }.run {
            super.onBackPressed()
        }
    }

    private fun receiverOrderAccepted(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody) {
        receiveOrderViewModel.vmAcceptedReceiverOrder(receiveOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                res?.set(
                    loading = {
                        Loader.progressDialog?.show()
                    },
                    success = {
                        Loader.progressDialog?.dismiss()
                        startActivity(Intent(this, ProcessOrderActivity::class.java).apply {
                            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                            putExtra(ProcessOrderActivity.POST_TAB, 0)
                        })
                        finish()
                    },
                    error = {
                        Loader.progressDialog?.dismiss()
                    }
                )
            }
        })
    }


    private fun receiverOrderCancelled(receiveOrderBody: com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody) {
        receiveOrderViewModel.vmCancelledReceiverOrder(receiveOrderBody).observe(this, {
            it.getContentIfNotHandled().let { res ->
                res?.set(
                    error = { Loader.progressDialog?.dismiss() },
                    success = {
                        Loader.progressDialog?.dismiss().run {
                            finish()
                        }
                    },
                    loading = { Loader.progressDialog?.show() }
                )
            }
        })
    }

    companion object {
        const val KEY_DATA_NOTIFY = "data-notif"
    }
}