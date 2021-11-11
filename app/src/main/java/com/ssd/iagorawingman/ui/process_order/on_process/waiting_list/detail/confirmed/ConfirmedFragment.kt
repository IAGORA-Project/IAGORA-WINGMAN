package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmed

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListDetailConfirmedBinding

class ConfirmedFragment : Fragment(R.layout.fragment_on_process_waiting_list_detail_confirmed) {

    private var _binding: FragmentOnProcessWaitingListDetailConfirmedBinding? = null
    private val binding get() = _binding as FragmentOnProcessWaitingListDetailConfirmedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessWaitingListDetailConfirmedBinding.bind(view)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}