package com.iagora.wingman.app.main_menu.presentation.home


import androidx.navigation.fragment.findNavController
import com.iagora.wingman.app.R
import com.iagora.wingman.app.databinding.FragmentHomeBinding
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class HomeFragment : BaseFragment<FragmentHomeBinding>(R.layout.fragment_home,
    { FragmentHomeBinding.bind(it) }) {

    private val viewModel: HomeViewModel by viewModel()

    override fun setView() {
        handleViewAction()

        viewModel.dataWingman.collectWhenStarted(viewLifecycleOwner) {
            binding.apply {
                Timber.d("wingman info : $it")
            }
        }
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
            moveToProcessOrder(1)
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
            moveToProcessOrder(0)
        }

        binding.incMenuWingman.btnPasar.setOnClickListener {
            findNavController().navigate(HomeFragmentDirections.homeNavigateToMarket())
        }
    }


    private fun moveToProcessOrder(startingTab: Int) {
        findNavController().navigate(
           HomeFragmentDirections.homeNavigateToProcessOrder(
                startingTab
            )
        )

    }
}