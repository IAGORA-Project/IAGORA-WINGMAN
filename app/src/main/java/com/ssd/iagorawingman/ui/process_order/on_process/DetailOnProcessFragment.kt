package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentDetailOnProcessBinding

class DetailOnProcessFragment : Fragment(R.layout.fragment_detail_on_process) {

    private lateinit var binding: FragmentDetailOnProcessBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailOnProcessBinding.bind(view)
    }



}