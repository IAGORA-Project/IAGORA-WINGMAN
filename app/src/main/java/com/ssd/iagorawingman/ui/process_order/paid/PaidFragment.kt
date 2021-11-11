package com.ssd.iagorawingman.ui.process_order.paid

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentPaidBinding


class PaidFragment : Fragment(R.layout.fragment_paid) {

    private lateinit var binding:FragmentPaidBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentPaidBinding.bind(view)
    }


}