package com.ssd.iagorawingman.ui.process_order.on_process.detail

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.FragmentDetailOnProcessBinding
import com.ssd.iagorawingman.utils.FormatCurrency
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailOnProcessFragment : Fragment(R.layout.fragment_detail_on_process) {

    private lateinit var binding: FragmentDetailOnProcessBinding
    private lateinit var adapter: DetailOnProcessProductAdapter
    private val args by navArgs<DetailOnProcessFragmentArgs>()
    private val viewModel: OnProcessDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailOnProcessBinding.bind(view)


        viewModel.setIdTransaction(args.idTransaction)
        handleAdapter()
        subscribeToViewModel()

    }


    private fun handleAdapter() {
        adapter = DetailOnProcessProductAdapter()
        binding.containerListItem.rvItemProduct.adapter = adapter
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.vmGetDetailWaitingOnProcess.collectLatest { res ->
                    res.apply {
                        when (status) {
                            Status.SUCCESS -> {
                                if (data != null) {
                                    handleUISuccess(data.success)
                                }
                            }

                            Status.LOADING -> {

                            }

                            Status.ERROR -> {

                            }
                        }
                    }
                }
            }
        }
    }

    private fun handleUISuccess(data: ProcessOrder.DetailWaitingOnProcess.Success) {
        data.apply {
            adapter.differ.submitList(listProduct)

            binding.apply {
                containerPerson.apply {
                    with(data.dataUser) {
                        tvNamePerson.text = fullName
                        tvPhoneNumberPerson.text = PhoneNumberUtils.formatNumber(
                            phoneNumber,
                            Locale.getDefault().country
                        )
                        shapeIvPerson.loadPhotoProfile(imgProfile)
                    }
                }

                containerListItem.apply {
                    tvStoreName.text = data.storeInfo.storeName

                    containerHandlingFee.apply {
                        tvNameItem.text =
                            requireActivity().resources.getString(R.string.text_handling_fee_title)
                        btnBargain.text =
                            requireActivity().resources.getString(R.string.text_change)
                        tilPriceBargain.hint =
                            requireActivity().resources.getString(R.string.text_hint_handling_fee)
                        tvItemPrice.formatPrice(handlingFee.toString())
                    }
                }

                containerServicesDelivery.apply {
                    with(data.address) {
                        tvUserLocation.text = details
                        tvDelivery.text = if (deliveryServices == "null") "-" else deliveryServices
                        tvDeliveryFee.formatPrice(deliveryFee.toString())
                    }
                }

                containerListBill.apply {
                    tvTotalPriceProductValue.formatPrice(totalPriceProduct.toString())
                    tvDiscountVoucherValue.text =
                        StringBuilder("- ${FormatCurrency.getCurrencyRp(discount.toDouble())}")
                    tvSubTotalValue.formatPrice(subTotal.toString())
                    tvDeliveryFeeValue.formatPrice(deliveryFee.toString())
                    tvHandlingFeeValue.formatPrice(handlingFee.toString())
                    tvPlatformFeeValue.formatPrice(platformFee.toString())
                    tvGrandTotalValue.formatPrice(grandTotal.toString())
                }
            }
        }
    }


}