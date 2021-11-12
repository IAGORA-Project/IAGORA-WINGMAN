package com.ssd.iagorawingman.ui.main_menu.home


import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentHomeBinding
import com.ssd.iagorawingman.ui.main_menu.MainViewModel
import com.ssd.iagorawingman.ui.pasar.list_pasar.ListPasarActivity
import com.ssd.iagorawingman.utils.FlowProcessOrder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : Fragment() {

    private lateinit var binding: FragmentHomeBinding
    private val mainViewModel: MainViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        handleViewAction()

        mainViewModel.getSharedwingmanInfo.observe(viewLifecycleOwner, {
            println("dgvdfgvdfgdfgdfgfgh $it")
        })
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

    private fun handleViewAction() {
        binding.incCardInfomration.cvFinishOrder.setOnClickListener {
            moveToProcessOrder(FlowProcessOrder.FINISH.ordinal)
        }



        binding.switchActiveStatus.isChecked = true
        binding.tvTitleActiveStatus.text = "Layanan Sedang Aktif"



        binding.switchActiveStatus.setOnCheckedChangeListener { buttonView, isChecked ->
            println("KJDHJDKHJKDHDKD $isChecked")
            if (isChecked) {
                binding.tvTitleActiveStatus.text = "Layanan Sedang Aktif"
            } else {
                binding.tvTitleActiveStatus.text = "Layanan Sedang Tidak Aktif"
            }
        }

        binding.incMenuWingman.btnPesanan.setOnClickListener {
            moveToProcessOrder(FlowProcessOrder.WAITING_CONFIRMATION.ordinal)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            startActivity(Intent(context, ListPasarActivity::class.java))
        }
    }


    private fun moveToProcessOrder(startingTab: Int) {
        findNavController().navigate(
            HomeFragmentDirections.actionNavigationHomeToProcessOrderActivity(startingTab)
        )
    }


}