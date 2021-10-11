package com.ssd.iagorawingman.ui.main_menu.home

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentHomeBinding
import com.ssd.iagorawingman.ui.chat.ChatActivity
import com.ssd.iagorawingman.ui.pasar.list_pasar.ListPasarActivity
import com.ssd.iagorawingman.ui.process_order.ProcessOrderActivity

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleAction()
    }


    override fun onResume() {
        super.onResume()

        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.statusBarColor = requireActivity().getColor(R.color.redPrimary)
    }

    override fun onPause() {
        super.onPause()

        val window = requireActivity().window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.WHITE);
    }

    private fun handleAction(){
        binding.incCardInfomration.cvFinishOrder.setOnClickListener {
            ProcessOrderActivity.newInstance(requireContext(), 3)
        }

        binding.ivButtonChat.setOnClickListener {
            ChatActivity.newInstance(requireContext())
        }

        binding.incMenuWingman.PasarGroup.setOnClickListener {
            ProcessOrderActivity.newInstance(requireContext(), 0)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            startActivity(Intent(context, ListPasarActivity::class.java))
        }


    }

}