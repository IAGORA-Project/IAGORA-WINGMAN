package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListBinding
import com.ssd.iagorawingman.ui.process_order.ProcessOrderContainerFragmentDirections
import com.ssd.iagorawingman.utils.Constants.WAITING_CONFIRMATION
import com.ssd.iagorawingman.utils.Constants.WAITING_CONFIRMED
import com.ssd.iagorawingman.utils.Constants.WAITING_PAYMENT
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class WaitingListFragment : Fragment(R.layout.fragment_on_process_waiting_list) {
    private var _binding: FragmentOnProcessWaitingListBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListBinding
    private lateinit var adapter: WaitingListAdapter
    private val viewModel: WaitingListViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
    }


    private fun handleAdapter() {
        adapter = WaitingListAdapter()
        binding.rvListOrder.adapter = adapter

        val type = arguments?.getString(PAGE_TYPE)
        viewModel.setTypeWaiting(type as String)
        setDestination(type)
    }

    private fun setDestination(type: String) {
        adapter.setOnItemClickListener { idTransaction ->
            when (type) {

                WAITING_CONFIRMATION -> {
                    findNavController().navigate(
                        ProcessOrderContainerFragmentDirections.moveToConfirmation(idTransaction)
                    )
                }

                WAITING_CONFIRMED -> {

                }

                WAITING_PAYMENT -> {

                }
            }
        }
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {


                viewModel.vmGetWaitingList.collectLatest { res ->
                    when (res.status) {
                        Status.LOADING -> {

                        }
                        Status.SUCCESS -> {
                            adapter.differ.submitList(res.data?.success)
                            binding.tvNumberOfOrder.text = res.data?.success?.size.toString()
                        }

                        Status.ERROR -> {

                        }
                    }

                }

            }
        }
    }

    companion object {
        const val PAGE_TYPE = "PAGE_TYPE"
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}