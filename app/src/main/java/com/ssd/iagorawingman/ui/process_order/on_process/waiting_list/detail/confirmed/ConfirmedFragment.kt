package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmed

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListDetailConfirmedBinding
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ConfirmedFragment : Fragment(R.layout.fragment_on_process_waiting_list_detail_confirmed) {

    private var _binding: FragmentOnProcessWaitingListDetailConfirmedBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListDetailConfirmedBinding
    private val viewModel: ConfirmedViewModel by viewModel()
    private val args by navArgs<ConfirmedFragmentArgs>()

    lateinit var adapter: ConfirmedAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListDetailConfirmedBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
    }


    private fun handleAdapter() {
        adapter = ConfirmedAdapter()
        binding.containerListItem.rvItemProduct.adapter = adapter
        viewModel.setIdTransaction(args.idTransaction)
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetDetailConfirmed.collectLatest { res ->
                    res.apply {
                        when (status) {
                            Status.SUCCESS -> {
                                handleUISuccess(data?.success as ProcessOrder.DetailWaitingOnProcess.Success)
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

    private fun handleUISuccess(data: ProcessOrder.DetailWaitingOnProcess.Success) {

        with(data) {

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

                containerActionOrder.apply {
                    btnAcceptOrder.setOnClickListener {

                    }
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}