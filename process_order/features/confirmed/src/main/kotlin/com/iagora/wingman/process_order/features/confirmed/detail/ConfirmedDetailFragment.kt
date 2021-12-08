package com.iagora.wingman.process_order.features.confirmed.detail

import android.telephony.PhoneNumberUtils
import androidx.navigation.fragment.navArgs
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.commons.views.helper.SetImage.loadPhotoProfile
import com.iagora.wingman.commons.views.helper.Util.hide
import com.iagora.wingman.commons.views.helper.Util.show
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

    private val detailViewModel: ConfirmedDetailViewModel by viewModel()


    private val args by navArgs<ConfirmedDetailFragmentArgs>()
    private lateinit var detailAdapter: ConfirmedDetailAdapter

    override fun setView() {
        handleAdapter()
        subscribeToViewModel()
    }


    private fun handleAdapter() {
        detailAdapter = ConfirmedDetailAdapter()
        binding.containerMainLayoutConfirmed.containerListItem.rvItemProduct.adapter = detailAdapter

        detailViewModel.setIdTransaction(args.idTransaction)
    }

    private fun subscribeToViewModel() {
        detailViewModel.vmGetDetailConfirmed.collectWhenStarted(this) { res ->
            binding.handleUI(res)
        }
    }

    private fun FragmentDetailConfirmedBinding.handleUI(res: Resource<ProcessOrder.DetailWaitingOnProcess>) {
        res.apply {
            set(
                success = {
                    containerLoadingConfirmed.root.hide().run {
                        containerMainLayoutConfirmed.root.show().run {
                            data?.success?.handleUISuccess()
                        }
                    }
                },
                error = { containerLoadingConfirmed.root.hide() },
                loading = { containerLoadingConfirmed.root.show() }
            )
        }
    }


    private fun ProcessOrder.DetailWaitingOnProcess.Success.handleUISuccess() {
        detailAdapter.submitList(listProduct)
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


}