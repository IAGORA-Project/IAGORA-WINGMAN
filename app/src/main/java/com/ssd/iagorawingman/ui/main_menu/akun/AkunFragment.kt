package com.ssd.iagorawingman.ui.main_menu.akun

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.ssd.iagorawingman.databinding.FragmentAkunBinding
import com.ssd.iagorawingman.ui.main_menu.MainViewModel
import com.ssd.iagorawingman.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel

class AkunFragment : Fragment() {

    private lateinit var binding: FragmentAkunBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentAkunBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }



}