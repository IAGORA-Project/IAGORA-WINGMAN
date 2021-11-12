package com.ssd.iagorawingman.ui.process_order.sent

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentSentBinding

class SentFragment : Fragment(R.layout.fragment_sent) {
    private lateinit var binding: FragmentSentBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSentBinding.bind(view)
    }

}