package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation

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
import com.ssd.iagorawingman.ui.process_order.waiting_list.WaitingListAdapter
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConfirmationFragment : Fragment(R.layout.fragment_on_process_waiting_list) {

    private var _binding: FragmentOnProcessWaitingListBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListBinding
    private lateinit var adapter: WaitingListAdapter
    private val viewModel: ConfirmationViewModel by sharedViewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
        setupDestinationToDetail()
    }


    private fun handleAdapter() {
        adapter = WaitingListAdapter()
        binding.rvListOrder.adapter = adapter
        viewModel.initViewModelConfirmation()
    }

    private fun setupDestinationToDetail() {
        adapter.setOnItemClickListener { idTransaction ->
            findNavController().navigate(
                ProcessOrderContainerFragmentDirections.moveToDetailConfirmation(idTransaction)
            )
        }
    }

    private fun subscribeToViewModel() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.vmGetConfirmationList.collectLatest { res ->
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


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}