package com.ssd.iagorawingman.ui.main_menu.home


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentHomeBinding
import com.ssd.iagorawingman.ui.chat.ChatActivity
import com.ssd.iagorawingman.ui.main_menu.MainViewModel
import com.ssd.iagorawingman.ui.pasar.list_pasar.ListPasarActivity
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment(R.layout.fragment_home) {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by viewModel()


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentHomeBinding.bind(view)
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
        window.statusBarColor = Color.WHITE;
    }

    private fun handleAction() {
        binding.incCardInfomration.cvFinishOrder.setOnClickListener {
            moveToProcessOrder(3)
        }

        binding.ivButtonChat.setOnClickListener {
            ChatActivity.newInstance(requireContext())
        }

        binding.incMenuWingman.btnPesanan.setOnClickListener {
            moveToProcessOrder(0)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            startActivity(Intent(context, ListPasarActivity::class.java))
        }
    }

    private fun moveToProcessOrder(startingTab: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToProcessOrderActivity()
                .setPositionTab(startingTab)
        )
    }


}