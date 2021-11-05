package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentWaitingListBinding
import com.ssd.iagorawingman.ui.process_order.ProcessOrderContainerFragmentDirections
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class WaitingListFragment : Fragment(R.layout.fragment_waiting_list) {
    private var _binding: FragmentWaitingListBinding? = null
    private val binding get() = _binding
    private lateinit var adapter: WaitingListAdapter
    private val viewModel: WaitingListViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentWaitingListBinding.bind(view)

        handleAdapter()
        setTypeWaiting()
        subscribeToViewModel()
    }

    private fun handleAdapter() {
        adapter = WaitingListAdapter()
        binding?.rvListOrder?.adapter = adapter
    }

    private fun setTypeWaiting() {
        val pos = arguments?.getInt(PAGE_TYPE) ?: 0
        val typeWaiting =
            requireActivity().resources.getStringArray(R.array.path_response_waiting_list)[pos]
        viewModel.setTypeWaiting(typeWaiting)
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
                            binding?.tvNumberOfOrder?.text = res.data?.success?.size.toString()
                        }

                        Status.ERROR -> {

                        }
                    }


                    adapter.setOnItemClickListener { idTransaction ->
                        findNavController().navigate(
                            ProcessOrderContainerFragmentDirections.actionProcessOrderContainerFragmentToDetailOnProcessFragment(
                                idTransaction
                            )
                        )
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