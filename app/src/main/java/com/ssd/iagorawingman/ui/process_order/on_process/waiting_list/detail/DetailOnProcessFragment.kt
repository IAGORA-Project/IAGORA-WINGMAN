package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.snackbar.Snackbar
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.databinding.FragmentOnProcessDetailBinding
import com.ssd.iagorawingman.utils.FormatCurrency
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Resource
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class DetailOnProcessFragment : Fragment(R.layout.fragment_on_process_detail) {

    private lateinit var binding: FragmentOnProcessDetailBinding
    private lateinit var adapter: DetailOnProcessProductAdapter
    private val args by navArgs<DetailOnProcessFragmentArgs>()
    private val viewModel: OnProcessDetailViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnProcessDetailBinding.bind(view)


        viewModel.setIdTransaction(args.idTransaction)
        handleAdapter()
        subscribeToViewModel()
        getFeedBackPost(viewModel.vmGetFeedBackBargainPrice)
        getFeedBackPost(viewModel.vmGetFeedBackActionTransaction)
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
                                    postBargain(data.success.idTransaction)
                                }
                            }

                            Status.LOADING -> {
                                Toast.makeText(requireContext(), "LOADING", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            Status.ERROR -> {

                            }
                        }
                    }
                }
            }
        }
    }

    private fun postBargain(idTransaction: String) {
        adapter.setOnItemClickListener { body ->
            viewModel.sendBargainPrice(
                BargainBody(
                    idProduct = body.idProduct,
                    idTransaction = idTransaction,
                    uom = body.uom,
                    newBargain = body.newBargain
                )
            )
        }
    }

    private fun postActionTransaction(idTransaction: String, typeAction: String) {
        viewModel.sendActionTransaction(idTransaction, typeAction)
    }

    private fun getFeedBackPost(vmFeedback: SharedFlow<Resource<ProcessOrder.Global>>) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                vmFeedback.collectLatest { res ->
                    res.apply {
                        when (res.status) {
                            Status.SUCCESS -> {
                                Snackbar.make(
                                    requireView(),
                                    data?.success ?: "Success",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }
                            Status.LOADING -> {
                                Snackbar.make(
                                    requireView(),
                                    res.message ?: "loading",
                                    Snackbar.LENGTH_SHORT
                                )
                                    .show()
                            }
                            Status.ERROR -> {
                                Snackbar.make(
                                    requireView(),
                                    res.message ?: "error",
                                    Snackbar.LENGTH_SHORT
                                ).show()
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



                containerActionOrder.apply {

                    val typeAction =
                        requireActivity().resources.getStringArray(R.array.path_type_action_transaction)
                    btnAcceptOrder.setOnClickListener {
                        postActionTransaction(idTransaction, "accept")
                    }

                    btnCancelOrder.setOnClickListener {
                        postActionTransaction(idTransaction, typeAction[1])
                    }
                }
            }
        }
    }


}