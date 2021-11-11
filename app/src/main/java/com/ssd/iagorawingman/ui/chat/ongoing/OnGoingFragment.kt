package com.ssd.iagorawingman.ui.chat.ongoing

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnGoingBinding


class OnGoingFragment : Fragment() {

    private lateinit var binding: FragmentOnGoingBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentOnGoingBinding.inflate(inflater, container, false)
        return binding.root
    }


}