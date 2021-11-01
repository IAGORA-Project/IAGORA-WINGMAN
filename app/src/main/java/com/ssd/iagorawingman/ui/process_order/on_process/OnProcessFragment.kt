package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessBinding
import com.ssd.iagorawingman.utils.Status
import kotlinx.coroutines.flow.collectLatest
import org.koin.androidx.viewmodel.ext.android.viewModel


class OnProcessFragment : Fragment(R.layout.fragment_on_process) {
    private lateinit var binding: FragmentOnProcessBinding
    private lateinit var adapter: OnProcessAdapter
    private val viewModel: OnProcessViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnProcessBinding.bind(view)

        handleAdapter()
        subscribeToViewModel()
        handleUI()
    }

    private fun handleAdapter() {
        adapter = OnProcessAdapter()
        binding.rvListOrder.adapter = adapter
    }

    private fun subscribeToViewModel() {
        viewModel.vmGetAllListWaiting.observe(viewLifecycleOwner, { res ->
            when (res.status) {
                Status.LOADING -> {

                }
                Status.SUCCESS -> {
                    viewModel.setDataUI(res.data!!)
                    Toast.makeText(requireContext(), "setData", Toast.LENGTH_SHORT).show()
                }
            }
        })


    }

    private fun handleUI() {
        lifecycleScope.launchWhenStarted {
            viewModel.dataUI.collectLatest {
                adapter.differ.submitList(it.success)
                Toast.makeText(requireContext(), "ui", Toast.LENGTH_SHORT).show()
                adapter.setOnItemClickListener {


            }
        }
    }


}