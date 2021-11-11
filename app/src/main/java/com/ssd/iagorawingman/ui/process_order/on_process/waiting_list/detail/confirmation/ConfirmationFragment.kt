package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmation

import android.os.Bundle
import android.telephony.PhoneNumberUtils
import android.util.Log
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListDetailConfirmationBinding
import com.ssd.iagorawingman.utils.FormatCurrency.formatPrice
import com.ssd.iagorawingman.utils.Other.setupTextWithBtn
import com.ssd.iagorawingman.utils.Resource
import com.ssd.iagorawingman.utils.SetImage.loadPhotoProfile
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ConfirmationFragment :
    Fragment(R.layout.fragment_on_process_waiting_list_detail_confirmation) {

    private var _binding: FragmentOnProcessWaitingListDetailConfirmationBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListDetailConfirmationBinding
    private val viewModel: ConfirmationViewModel by viewModel()
    private val args by navArgs<ConfirmationFragmentArgs>()

    lateinit var adapter: ConfirmationAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListDetailConfirmationBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
    }

    private fun handleAdapter() {
        adapter = ConfirmationAdapter()
        binding.containerListItem.rvItemProduct.adapter = adapter
        viewModel.setIdTransaction(args.idTransaction)
    }


    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetDetailConfirmation.collectLatest { res ->
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

    private fun handleUISuccess(data: ProcessOrder.DetailWaitingOnProcess.Success) {
        binding.apply {
            with(data) {
                adapter.differ.submitList(listProduct)

                Log.e("ID", idTransaction)
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
                        tilPriceBargain.editText?.setupTextWithBtn(btnBargain)
                        tvItemPrice.formatPrice(handlingFee.toString())
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

    private fun postActionTransaction(idTransaction: String, typeAction: String) {
        viewModel.sendActionTransaction(idTransaction, typeAction)
        getFeedActionTransaction()
    }

    private fun getFeedActionTransaction() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                viewModel.vmGetFeedBackActionTransaction.collectLatest { res ->
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
        adapter.setOnItemClickListener { body, textInputLayout, textView ->
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
        oldPrice: String
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
        oldPrice: String
    ) {
        res.apply {
            when (status) {
                Status.SUCCESS -> {
                    with(textInputLayout) {
                        if (oldPrice == textView.text.toString()
                        ) {
                            textView.formatPrice(
                                editText?.text?.toString() as String
                            )
                            editText?.text?.clear()
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