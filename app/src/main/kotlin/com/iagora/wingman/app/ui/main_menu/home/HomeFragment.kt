package com.iagora.wingman.app.ui.main_menu.home


import androidx.navigation.fragment.findNavController
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentHomeBinding
import com.iagora.wingman.app.ui.main_menu.MainViewModel
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.helper.FlowProcessOrder
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
            if (isChecked) {
                binding.tvTitleActiveStatus.text = StringBuilder("Layanan Sedang Aktif")
            } else {
                binding.tvTitleActiveStatus.text = StringBuilder("Layanan Sedang Tidak Aktif")
            }
        }

        binding.incMenuWingman.btnPesanan.setOnClickListener {
            moveToProcessOrder(FlowProcessOrder.WAITING_CONFIRMATION.ordinal)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.homeNavigateToMarket())
        }
    }


    private fun moveToProcessOrder(startingTab: Int) {
        findNavController().navigate(HomeFragmentDirections.homeNavigateToProcessOrder(startingTab))

    }
}