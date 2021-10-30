package com.ssd.iagorawingman.ui.process_order.finished

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentFinishedBinding


class FinishedFragment : Fragment(R.layout.fragment_finished) {
   private lateinit var binding:FragmentFinishedBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentFinishedBinding.bind(view)
    }
}