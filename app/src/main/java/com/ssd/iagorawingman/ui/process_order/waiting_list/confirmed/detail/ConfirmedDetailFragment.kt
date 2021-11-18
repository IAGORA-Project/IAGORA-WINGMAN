package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.detail

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListDetailConfirmedBinding
import com.ssd.iagorawingman.ui.process_order.ProcessOrderViewModel
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Other.collectWhenStarted
import com.ssd.iagorawingman.utils.Other.hide
import com.ssd.iagorawingman.utils.Other.show
import com.ssd.iagorawingman.utils.Resource
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ConfirmedDetailFragment :
    Fragment(R.layout.fragment_on_process_waiting_list_detail_confirmed) {

    private var _binding: FragmentOnProcessWaitingListDetailConfirmedBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListDetailConfirmedBinding

    private val detailViewModel: ConfirmedDetailViewModel by viewModel()
    private val processOrderViewModel: ProcessOrderViewModel by sharedViewModel()

    private val args by navArgs<ConfirmedDetailFragmentArgs>()

    private lateinit var detailAdapter: ConfirmedDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListDetailConfirmedBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
    }


    private fun handleAdapter() {
        detailAdapter = ConfirmedDetailAdapter()
        binding.containerMainLayoutConfirmed.containerListItem.rvItemProduct.adapter = detailAdapter

        detailViewModel.setIdTransaction(args.idTransaction)
        processOrderViewModel.setInitPositionTab(FlowProcessOrder.CONFIRMATION.ordinal)
    }

    private fun subscribeToViewModel() {
        detailViewModel.vmGetDetailConfirmed.collectWhenStarted(this) { res ->
            binding.handleUI(res)
        }
    }

    private fun FragmentOnProcessWaitingListDetailConfirmedBinding.handleUI(res: Resource<ProcessOrder.DetailWaitingOnProcess>) {
        res.apply {
            when (status) {
                Status.SUCCESS -> {
                    containerLoadingConfirmed.root.hide().run {
                        containerMainLayoutConfirmed.root.show().run {
                            data?.success?.handleUISuccess()
                        }
                    }
                }

                Status.LOADING -> {
                    containerLoadingConfirmed.root.show()
                }

                Status.ERROR -> {
                    containerLoadingConfirmed.root.hide()
                }
            }
        }
    }


    private fun ProcessOrder.DetailWaitingOnProcess.Success.handleUISuccess() {
        detailAdapter.differ.submitList(listProduct)
        binding.containerMainLayoutConfirmed.apply {
            containerPerson.apply {
                with(this@handleUISuccess.dataUser) {
                    tvNamePerson.text = fullName
                    tvPhoneNumberPerson.text = PhoneNumberUtils.formatNumber(
                        phoneNumber,
                        Locale.getDefault().country
                    )
                    shapeIvPerson.loadPhotoProfile(imgProfile)
                }
            }

            containerListItem.apply {
                tvStoreName.text = this@handleUISuccess.storeInfo.storeName

                containerHandlingFee.apply {
                    tvHandleFeeValue.formatPrice(handlingFee.toString())
                }
            }

            containerListBill.apply {
                    tvTotalPriceProductValue.formatPrice(totalPriceProduct.toString())
                    tvSubTotalValue.formatPrice(subTotal.toString())
                    tvHandlingFeeValue.formatPrice(handlingFee.toString())
                    tvPlatformFeeValue.formatPrice(platformFee.toString())
                    tvGrandTotalValue.formatPrice(grandTotal.toString())
                }
            }

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}