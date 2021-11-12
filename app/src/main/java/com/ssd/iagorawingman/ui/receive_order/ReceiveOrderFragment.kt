package com.ssd.iagorawingman.ui.receive_order

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.gson.Gson
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.data.source.remote.body.ReceiveOrderBody
import com.ssd.iagorawingman.databinding.FragmentReceiveOrderBinding
import com.ssd.iagorawingman.utils.Loader
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class ReceiveOrderFragment : Fragment(R.layout.fragment_receive_order) {
    private var _binding: FragmentReceiveOrderBinding? = null
    private val binding get() = _binding
    private val receiveOrderViewModel: ReceiveOrderViewModel by viewModel()
    private lateinit var receiveOrderProductAdapter: ReceiveOrderProductAdapter
    private var dataNotif: String = ""
    private var receiveOrderBody: ReceiveOrderBody? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentReceiveOrderBinding.bind(view)

        initBundle()
        Loader.handleLoading(requireContext())
    }


    private fun initBundle() {
        dataNotif = requireActivity().intent.getStringExtra("data-notif").toString()
        receiveOrderBody = Gson().fromJson(dataNotif, ReceiveOrderBody::class.java)

        receiveOrderBody?.let { data -> handleViewAction(data) }
        receiveOrderBody?.listProduct?.let { listProduct -> handleAdapterListProduct(listProduct) }
        Log.d("dataNotifdataNotif", receiveOrderBody.toString())
        Log.d("dfgbdsgsdgsdgdsgsd", dataNotif)
    }


    private fun handleViewAction(data: ReceiveOrderBody) {
        binding?.apply {
            tvUserName.text = data.dataUser?.fullName
            Glide
                .with(this@ReceiveOrderFragment)
                .load(data.dataUser?.imgProfile)
                .into(ivPicUser)


            incBottom.btnAccepted.setOnClickListener {
                receiverOrderAccepted(data)
            }

            incBottom.btnCancel.setOnClickListener {
                receiverOrderCancelled(data)
            }
        }
    }

    private fun handleAdapterListProduct(data: ArrayList<ReceiveOrderBody.Product>) {
        receiveOrderProductAdapter = ReceiveOrderProductAdapter(data)
        binding?.rvListProduct?.apply {
            adapter = receiveOrderProductAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    private fun moveToProcessOrder() {
        findNavController().navigate(
            ReceiveOrderFragmentDirections.moveToProcessOrder(
                0
            )
        ).run {
            requireActivity().finish()
        }
    }

    private fun receiverOrderAccepted(receiveOrderBody: ReceiveOrderBody) {
        println("JDHJDHDJHD ${receiveOrderBody}")
        receiveOrderViewModel.vmAcceptedReceiverOrder(receiveOrderBody)
            .observe(viewLifecycleOwner, {
                it.getContentIfNotHandled().let { res ->
                    when (res?.status) {
                        Status.LOADING -> {
                            println("KJDBHJDHDJHDD ${res.message}")
                            Loader.progressDialog?.show()
                        }
                        Status.SUCCESS -> {
                            println("KJDBHJDHDJHDD ${res.data}")
                            Loader.progressDialog?.dismiss()
                            moveToProcessOrder()
                        }
                        Status.ERROR -> {
                            Loader.progressDialog?.dismiss()
                        }
                    }
                }
            })
    }


    private fun receiverOrderCancelled(receiveOrderBody: ReceiveOrderBody) {
        receiveOrderViewModel.vmCancelledReceiverOrder(receiveOrderBody)
            .observe(viewLifecycleOwner, {
                it.getContentIfNotHandled().let { res ->
                    when (res?.status) {
                        Status.LOADING -> {
                            println("KJDBHJDHDJHDD ${res.message}")
                            Loader.progressDialog?.show()
                        }
                        Status.SUCCESS -> {
                            println("KJDBHJDHDJHDD ${res.data}")
                            Loader.progressDialog?.dismiss()
                            requireActivity().finish()
                        }
                        Status.ERROR -> {
                            Loader.progressDialog?.dismiss()
                        }
                    }
                }
            })
    }


}