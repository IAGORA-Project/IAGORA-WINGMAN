package com.iagora.wingman.app.ui.main_menu.home


import android.content.Intent
import android.graphics.Color
import android.view.WindowManager
import androidx.navigation.fragment.findNavController
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentHomeBinding
import com.iagora.wingman.app.ui.main_menu.MainViewModel
import com.iagora.wingman.app.ui.pasar.list_pasar.ListPasarActivity
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.process_order.helper.FlowProcessOrder
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home,
    { FragmentHomeBinding.bind(it) }) {

    private val mainViewModel: MainViewModel by viewModel()

    override fun setView() {
        handleViewAction()


        mainViewModel.getSharedwingmanInfo.observe(viewLifecycleOwner, {
            println("dgvdfgvdfgdfgdfgfgh $it")
        })
    }

//    override fun onResume() {
//        super.onResume()
//
//        val window = requireActivity().window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = requireActivity().getColor(R.color.redPrimary)
//    }

//    override fun onPause() {
//        super.onPause()
//
//        val window = requireActivity().window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = Color.WHITE
//    }

    private fun handleViewAction() {
        binding.incCardInfomration.cvFinishOrder.setOnClickListener {
            moveToProcessOrder(FlowProcessOrder.FINISH.ordinal)
        }



        binding.switchActiveStatus.isChecked = true
        binding.tvTitleActiveStatus.text = StringBuilder("Layanan Sedang Aktif")



        binding.switchActiveStatus.setOnCheckedChangeListener { _, isChecked ->
            println("KJDHJDKHJKDHDKD $isChecked")
            if (isChecked) {
                binding.tvTitleActiveStatus.text = StringBuilder("Layanan Sedang Aktif")
            } else {
                binding.tvTitleActiveStatus.text = StringBuilder("Layanan Sedang Tidak Aktif")
            }
        }

        binding.incMenuWingman.btnPesanan.setOnClickListener {
            moveToProcessOrder(com.iagora.wingman.process_order.helper.FlowProcessOrder.WAITING_CONFIRMATION.ordinal)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            startActivity(Intent(context, ListPasarActivity::class.java))
        }
    }


    private fun moveToProcessOrder(startingTab: Int) {
        findNavController().navigate(HomeFragmentDirections.homeNavigateToProcessOrder(startingTab))

    }
}