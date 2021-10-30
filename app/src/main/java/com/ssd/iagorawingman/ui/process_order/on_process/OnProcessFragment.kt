package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessBinding


class OnProcessFragment : Fragment(R.layout.fragment_on_process) {
    private lateinit var binding: FragmentOnProcessBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentOnProcessBinding.bind(view)
    }




}