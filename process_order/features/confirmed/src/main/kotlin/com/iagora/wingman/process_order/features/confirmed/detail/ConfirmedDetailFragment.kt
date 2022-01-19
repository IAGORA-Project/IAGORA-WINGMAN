package com.iagora.wingman.process_order.features.confirmed.detail

import android.telephony.PhoneNumberUtils
import androidx.navigation.fragment.navArgs
import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.core.presentation.util.FormatCurrency.formatPrice
import com.iagora.wingman.core.presentation.util.SetImage.loadPhotoProfile
import com.iagora.wingman.core.presentation.util.Util.hide
import com.iagora.wingman.core.presentation.util.Util.show
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.set
import com.iagora.wingman.process_order.features.confirmed.R
import com.iagora.wingman.process_order.features.confirmed.databinding.FragmentDetailConfirmedBinding
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import com.iagora.wingman.process_order.viewmodels.ConfirmedDetailViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*


class ConfirmedDetailFragment :
    BaseFragment<FragmentDetailConfirmedBinding>(R.layout.fragment_detail_confirmed,
        { FragmentDetailConfirmedBinding.bind(it) }) {

    private val viewModel: ConfirmedDetailViewModel by viewModel()


    private val args by navArgs<ConfirmedDetailFragmentArgs>()
    private lateinit var detailAdapter: ConfirmedDetailAdapter

    override fun setView() {
        handleAdapter()
        viewModel.vmData.collectWhenStarted(viewLifecycleOwner) { data ->
            if (data == null) {
                subscribeToViewModel()
            } else {
                data.success.handleUISuccess()
            }
        }
    }


    private fun handleAdapter() {
        detailAdapter = ConfirmedDetailAdapter()
        binding.containerMainLayoutConfirmed.containerListItem.rvItemProduct.adapter = detailAdapter

        viewModel.setIdTransaction(args.idTransaction)
    }

    private fun subscribeToViewModel() {
        viewModel.vmGetDetailConfirmed.collectWhenStarted(viewLifecycleOwner) { res ->
            binding.handleUI(res)
        }
    }

    private fun FragmentDetailConfirmedBinding.handleUI(res: Resource<ProcessOrder.DetailWaitingOnProcess>) {
        res.apply {
            set(
                success = {
                    viewModel.setData(data)
                    data?.success?.handleUISuccess()
                },
                error = { containerLoadingConfirmed.root.hide() },
                loading = { containerLoadingConfirmed.root.show() }
            )
        }
    }


    private fun ProcessOrder.DetailWaitingOnProcess.Success.handleUISuccess() {
        detailAdapter.submitList(listProduct)


        with(binding) {
            containerLoadingConfirmed.root.hide()
            containerMainLayoutConfirmed.apply {
                root.show()

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


    }


}