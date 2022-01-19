package com.iagora.wingman.receive_order.features.main_features


import android.content.Intent
import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.Loader
import com.iagora.wingman.core.presentation.util.SetImage.loadPhotoProfile
import com.iagora.wingman.helper.set
import com.iagora.wingman.process_order.features.main_features.ProcessOrderActivity
import com.iagora.wingman.receive_order.features.main_features.databinding.ActivityReceiveOrderBinding
import com.iagora.wingman.receive_order.helper.model.body.ReceiveOrder
import org.koin.androidx.viewmodel.ext.android.viewModel


class ReceiveOrderActivity :
    BaseActivity<ActivityReceiveOrderBinding>({ ActivityReceiveOrderBinding.inflate(it) }) {

    private val viewModel: ReceiveOrderViewModel by viewModel()
    private lateinit var adapter: ReceiveOrderAdapter

    override fun setView() {
        handleAdapter()
        initUI()
        subscribeViewModel()

    }

    private fun initUI() {
        val body = intent.getParcelableExtra<ReceiveOrder>(KEY_DATA_NOTIFY) as ReceiveOrder
        adapter.submitList(body.listProduct)

        binding.apply {
            with(body) {
                dataUser.apply {
                    tvUserName.text = dataUser.fullName
                    ivPicUser.loadPhotoProfile(imgProfile)
                }

                incBottom.apply {
                    btnAccepted.setOnClickListener {
                        viewModel.postAcceptedOrder(body)
                    }

                    btnCancel.setOnClickListener {
                        viewModel.postCanceledOrder(body)
                    }
                }
            }
        }
    }


    private fun handleAdapter() {
        adapter = ReceiveOrderAdapter()
        binding.rvListProduct.adapter = adapter
        Loader.handleLoading(this)
    }

    private fun subscribeViewModel() {
        with(viewModel) {

            vmFeedbackAcceptedOrder.collectWhenStarted(this@ReceiveOrderActivity) { res ->
                res.set(
                    error = { Loader.progressDialog?.dismiss() },
                    success = { moveToProcessOrder() },
                    loading = { Loader.progressDialog?.show() }
                )
            }


            vmFeedbackCanceledOrder.collectWhenStarted(this@ReceiveOrderActivity) { res ->
                res.set(
                    error = { Loader.progressDialog?.dismiss() },
                    success = {
                        Loader.progressDialog?.dismiss().run {
                            finish()
                        }
                    },
                    loading = { Loader.progressDialog?.show() }
                )

            }
        }

    }

    private fun moveToProcessOrder() {
        Loader.progressDialog?.dismiss().run {

            startActivity(Intent(this@ReceiveOrderActivity,
                ProcessOrderActivity::class.java).apply {
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                putExtra(ProcessOrderActivity.POST_TAB, 0)
            })

        }.also {
            finish()
        }
    }

    override fun onBackPressed() {
        binding.incBottom.btnCancel.callOnClick()
    }


    companion object {
        const val KEY_DATA_NOTIFY = "data-notif"
    }
}