package com.iagora.wingman.receive_order.presentation


import com.google.android.material.snackbar.Snackbar
import com.iagora.wingman.core.presentation.base.BaseActivity
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.Loader
import com.iagora.wingman.core.presentation.util.SetImage.loadPhotoProfile
import com.iagora.wingman.core.presentation.util.UiEvent
import com.iagora.wingman.core.presentation.util.asString
import com.iagora.wingman.core.presentation.util.customPrimaryColor
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder
import com.iagora.wingman.receive_order.presentation.databinding.ActivityReceiveOrderBinding
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
        val body = intent.getParcelableExtra<ReceiveOrder>(KEY_DATA_NOTIFY)
        adapter.submitList(body?.listProduct)

        binding.apply {
            body?.dataUser?.apply {
                tvUserName.text = fullName
                ivPicUser.loadPhotoProfile(imgProfile)
            }

            incBottom.apply {
                btnAccepted.setOnClickListener {
                    viewModel.accept(body as ReceiveOrder)
                }

                btnCancel.setOnClickListener {
                    viewModel.cancel(body as ReceiveOrder)
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
        viewModel.eventFLow.collectWhenStarted(this) { event ->
            when (event) {
//                is ReceiveOrderEvent.MoveToProcessOrder -> moveToProcessOrder()
                is ReceiveOrderEvent.MoveToHome -> finish()
                is ReceiveOrderEvent.LoadingProcess -> {
                    if (event.isLoading) Loader.progressDialog?.show()
                    else Loader.progressDialog?.dismiss()
                }
                is UiEvent.CreateMessage -> {
                    Snackbar.make(binding.root, event.uiText.asString(this), Snackbar.LENGTH_SHORT)
                        .apply {
                            customPrimaryColor(this@ReceiveOrderActivity)
                        }.show()
                }
            }
        }
    }

//    private fun moveToProcessOrder() {
//        startActivity(Intent(
//            this@ReceiveOrderActivity,
//            ProcessOrderActivity::class.java
//        ).apply {
//            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//            putExtra(ProcessOrderActivity.POST_TAB, 0)
//        })
//        finish()
//    }

    override fun onBackPressed() {
        binding.incBottom.btnCancel.callOnClick()
    }

    companion object {
        const val KEY_DATA_NOTIFY = "data-notif"
    }
}