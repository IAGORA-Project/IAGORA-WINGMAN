package com.iagora.wingman.process_order.features.confirmation.detail

import android.telephony.PhoneNumberUtils
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenCreated
import com.iagora.wingman.commons.views.helper.FormatCurrency.formatPrice
import com.iagora.wingman.commons.views.helper.SetImage.loadPhotoProfile
import com.iagora.wingman.commons.views.helper.Util.clearFocus
import com.iagora.wingman.commons.views.helper.Util.hide
import com.iagora.wingman.commons.views.helper.Util.setupTextWithBtn
import com.iagora.wingman.commons.views.helper.Util.show
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.Status.*
import com.iagora.wingman.helper.set
import com.iagora.wingman.process_order.features.confirmation.R
import com.iagora.wingman.process_order.features.confirmation.databinding.FragmentDetailConfirmationBinding
import com.iagora.wingman.process_order.helper.FlowProcessOrder
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import com.iagora.wingman.process_order.viewmodels.ConfirmationDetailViewModel
import com.iagora.wingman.process_order.viewmodels.ConfirmationViewModel
import com.iagora.wingman.process_order.viewmodels.ConfirmedViewModel
import com.iagora.wingman.process_order.viewmodels.ProcessOrderViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ConfirmationDetailFragment :
    BaseFragment<FragmentDetailConfirmationBinding>(R.layout.fragment_detail_confirmation,
        { FragmentDetailConfirmationBinding.bind(it) }) {


    private val viewModel: ConfirmationDetailViewModel by viewModel()
    private val confirmationViewModel: ConfirmationViewModel by sharedViewModel()
    private val confirmedViewModel: ConfirmedViewModel by sharedViewModel()
    private val processOrderViewModel: ProcessOrderViewModel by sharedViewModel()

    private val args by navArgs<ConfirmationDetailFragmentArgs>()
    private lateinit var detailAdapter: ConfirmationDetailAdapter


    override fun setView() {
        handleAdapter()
        subscribeToViewModel()
    }

    private fun handleAdapter() {
        detailAdapter = ConfirmationDetailAdapter()
        binding.containerMainLayoutConfirmation.containerListItem.rvItemProduct.adapter =
            detailAdapter
        viewModel.setIdTransaction(args.idTransaction)
    }


    private fun subscribeToViewModel() {
        viewModel.vmGetDetailConfirmation.collectWhenCreated(this) { res ->
            binding.handleUI(res)
        }
    }


    private fun FragmentDetailConfirmationBinding.handleUI(res: Resource<ProcessOrder.DetailWaitingOnProcess>) {
        res.apply {
            set(
                success = {
                    containerLoadingConfirmation.root.hide().run {
                        containerMainLayoutConfirmation.root.show().run {
                            data?.success?.handleUISuccess()
                        }
                    }
                },
                loading = {
                    containerLoadingConfirmation.root.show()
                },
                error = {
                    containerLoadingConfirmation.root.hide()
                }
            )

        }
    }

    private fun ProcessOrder.DetailWaitingOnProcess.Success.handleUISuccess() {


        detailAdapter.submitList(listProduct)
        postBargain(idTransaction)

        binding.containerMainLayoutConfirmation.apply {

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
                    tilHandlingFeeBargain.editText?.setupTextWithBtn(btnHandlingFeeBargain)
                    tvHandlingFeeValue.formatPrice(handlingFee.toString())



                    btnHandlingFeeBargain.setOnClickListener {
                        postHandlingFee(idTransaction,
                            HandlingFee(
                                tilHandlingFeeBargain.editText?.text.toString()
                                    .toLong()))

                        clearFocus(tilHandlingFeeBargain.editText)

                        }
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
                        showDialog(0, idTransaction)
                    }
                    btnCancelOrder.setOnClickListener {
                        showDialog(1, idTransaction)
                    }
                }
        }

    }

    private fun postHandlingFee(
        idTransaction: String,
        handlingFee: HandlingFee,
    ) {
        viewModel.sendNewHandlingFee(idTransaction, handlingFee)
        getFeedHandlingFee()
    }

    private fun getFeedHandlingFee() {
        viewModel.vmGetFeedBackChangeHandlingFee.collectWhenCreated(this) { res ->
            setFeedBackHandlingFee(res)
        }
    }

    private fun setFeedBackHandlingFee(res: Resource<ProcessOrder.Global>) {
        res.apply {
            set(
                success = {
                    binding.containerMainLayoutConfirmation.containerListItem.containerHandlingFee.apply {
                        tilHandlingFeeBargain.apply {
                            tvHandlingFeeValue.formatPrice(editText?.text.toString()).run {
                                editText?.text?.clear()
                            }
                        }

                    }
                },
                error = {},
                loading = {}
            )
        }
    }

    private fun postActionTransaction(idTransaction: String, typeAction: String) {
        viewModel.sendActionTransaction(idTransaction, typeAction)
        getFeedActionTransaction(typeAction, idTransaction)
    }

    private fun getFeedActionTransaction(typeAction: String, idTransaction: String) {
        viewModel.vmGetFeedBackActionTransaction.collectWhenCreated(this) { res ->
            setFeedBackActionTransaction(res, typeAction, idTransaction)
        }
    }

    private fun setFeedBackActionTransaction(
        res: Resource<ProcessOrder.Global>,
        typeAction: String,
        idTransaction: String,
    ) {
        res.apply {
            set(
                error = {
                    Snackbar.make(
                        requireView(),
                        message ?: "error",
                        Snackbar.LENGTH_SHORT
                    ).show().run {
                        confirmationViewModel.initViewModelConfirmation()
                    }
                },
                success = { moveToAnotherPager(typeAction, idTransaction) },
                loading = {}
            )
        }
    }


    private fun moveToAnotherPager(typeAction: String, idTransaction: String) {
        confirmationViewModel.initViewModelConfirmation().run {
            if (typeAction == "accept") {
                confirmedViewModel.initViewModelConfirmed().also {
                    findNavController().navigate(
                        ConfirmationDetailFragmentDirections.moveToConfirmed(
                            idTransaction
                        )
                    )
                }.also {
                    processOrderViewModel.setPosTab(FlowProcessOrder.CONFIRMATION.ordinal)
                }
            } else {
                findNavController().popBackStack()

            }
        }
    }

    private fun showDialog(pos: Int, idTransaction: String) {
        val typeAction =
            requireActivity().resources.getStringArray(R.array.path_type_action_transaction)
        val message =
            requireActivity().resources.getStringArray(R.array.text_dialog_action_transaction)
        val title =
            requireActivity().resources.getStringArray(R.array.title_dialog_action_transaction)

        MaterialAlertDialogBuilder(
            requireContext(),
            R.style.AlertDialogTheme
        ).setMessage(message[pos])
            .setTitle(title[pos])
            .setPositiveButton(requireActivity().resources.getString(R.string.text_positive_button)) { dialogInterface, _ ->
                postActionTransaction(idTransaction, typeAction[pos])
                dialogInterface.dismiss()
            }
            .setNegativeButton(requireActivity().resources.getString(R.string.text_negative_button)) { dialogInterface, _ ->
                dialogInterface.dismiss()
            }.show()
    }


    private fun postBargain(idTransaction: String) {
        detailAdapter.setOnItemClickListener { body, textInputLayout, textView ->
            viewModel.sendBargainPrice(
                Bargain(
                    idProduct = body.idProduct,
                    idTransaction = idTransaction,
                    uom = body.uom,
                    newBargain = body.newBargain
                )
            )
            getFeedBackBargainPrice(textInputLayout, textView, textView.text.toString())
        }
    }

    private fun getFeedBackBargainPrice(
        textInputLayout: TextInputLayout,
        textView: TextView,
        oldPrice: String,
    ) {
        viewModel.vmGetFeedBackBargainPrice.collectWhenCreated(this) { res ->
            setFeedBackBargainPrice(res, textInputLayout, textView, oldPrice)
        }
    }

    private fun setFeedBackBargainPrice(
        res: Resource<ProcessOrder.Global>,
        textInputLayout: TextInputLayout,
        textView: TextView,
        oldPrice: String,
    ) {
        res.apply {
            set(
                error = {},
                success = {
                    with(textInputLayout) {
                        if (oldPrice == textView.text.toString()
                        ) {
                            textView.formatPrice(
                                editText?.text?.toString() as String
                            ).run {
                                editText?.text?.clear()
                            }
                        }
                    }
                },
                loading = {}
            )
        }
    }

}