package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.detail

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.body.HandlingFeeBody
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListDetailConfirmationBinding
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.ConfirmationViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.ConfirmedViewModel
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Other
import com.ssd.iagorawingman.utils.Other.setupTextWithBtn
import com.ssd.iagorawingman.utils.Resource
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ConfirmationDetailFragment :
    Fragment(R.layout.fragment_on_process_waiting_list_detail_confirmation) {

    private var _binding: FragmentOnProcessWaitingListDetailConfirmationBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListDetailConfirmationBinding

    private val viewModel: ConfirmationDetailViewModel by viewModel()
    private val confirmationViewModel: ConfirmationViewModel by sharedViewModel()
    private val confirmedViewModel: ConfirmedViewModel by sharedViewModel()

    private val args by navArgs<ConfirmationDetailFragmentArgs>()
    private lateinit var detailAdapter: ConfirmationDetailAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListDetailConfirmationBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
    }

    private fun handleAdapter() {
        detailAdapter = ConfirmationDetailAdapter()
        binding.containerListItem.rvItemProduct.adapter = detailAdapter
        viewModel.setIdTransaction(args.idTransaction)
    }


    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetDetailConfirmation.collectLatest { res ->
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

            detailAdapter.differ.submitList(listProduct)
            postBargain(idTransaction)

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
                        tilHandlingFeeBargain.editText?.setupTextWithBtn(btnHandlingFeeBargain)
                        tvHandlingFeeValue.formatPrice(handlingFee.toString())



                        btnHandlingFeeBargain.setOnClickListener {
                            postHandlingFee(idTransaction,
                                HandlingFeeBody(tilHandlingFeeBargain.editText?.text.toString()
                                    .toLong()))

                            Other.clearFocus(tilHandlingFeeBargain.editText)


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
    }

    private fun postHandlingFee(idTransaction: String, handlingFeeBody: HandlingFeeBody) {
        viewModel.sendNewHandlingFee(idTransaction, handlingFeeBody)
        getFeedHandlingFee()
    }

    private fun getFeedHandlingFee() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetFeedBackChangeHandlingFee.collectLatest { res ->
                    setFeedBackHandlingFee(res)
                }
            }
        }
    }

    private fun setFeedBackHandlingFee(res: Resource<ProcessOrder.Global>) {
        res.apply {
            when (status) {
                Status.SUCCESS -> {
                    binding.containerListItem.containerHandlingFee.apply {
                        tilHandlingFeeBargain.apply {
                            tvHandlingFeeValue.formatPrice(editText?.text.toString()).run {
                                editText?.text?.clear()
                            }
                        }

                    }
                }
                Status.LOADING -> {

                }
                Status.ERROR -> {

                }
            }
        }
    }

    private fun postActionTransaction(idTransaction: String, typeAction: String) {
        viewModel.sendActionTransaction(idTransaction, typeAction)
        getFeedActionTransaction(typeAction, idTransaction)
    }

    private fun getFeedActionTransaction(typeAction: String, idTransaction: String) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetFeedBackActionTransaction.collectLatest { res ->
                    setFeedBackActionTransaction(res, typeAction, idTransaction)
                }
            }
        }
    }

    private fun setFeedBackActionTransaction(
        res: Resource<ProcessOrder.Global>,
        typeAction: String,
        idTransaction: String,
    ) {
        res.apply {
            when (res.status) {
                Status.SUCCESS -> {
                    moveToAnotherPager(typeAction, idTransaction)
                }
                Status.LOADING -> {
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


    private fun moveToAnotherPager(typeAction: String, idTransaction: String) {
        confirmationViewModel.initViewModelConfirmation().run {
            if (typeAction == "accept") {
                confirmedViewModel.initViewModelConfirmed().run {
                    findNavController().navigate(
                        ConfirmationDetailFragmentDirections.moveToConfirmed(
                            idTransaction
                        )
                    )
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
                BargainBody(
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
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetFeedBackBargainPrice.collectLatest { res ->
                    setFeedBackBargainPrice(res, textInputLayout, textView, oldPrice)
                }
            }
        }

    }

    private fun setFeedBackBargainPrice(
        res: Resource<ProcessOrder.Global>,
        textInputLayout: TextInputLayout,
        textView: TextView,
        oldPrice: String,
    ) {
        res.apply {
            when (status) {
                Status.SUCCESS -> {
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
                }
                Status.LOADING -> {
                }
                Status.ERROR -> {
                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}