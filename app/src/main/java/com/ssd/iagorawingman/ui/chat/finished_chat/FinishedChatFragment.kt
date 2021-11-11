package com.ssd.iagorawingman.ui.chat.finished_chat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentFinishedChatBinding


class FinishedChatFragment : Fragment() {

    private lateinit var binding: FragmentFinishedChatBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentFinishedChatBinding.inflate(inflater, container, false)
        return binding.root
    }


}