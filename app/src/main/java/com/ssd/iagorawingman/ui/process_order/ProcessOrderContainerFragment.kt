package com.ssd.iagorawingman.ui.process_order

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentProcessOrderBinding
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.ConfirmationViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.ConfirmedViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.payment.PaymentViewModel
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Other.collectWhenStarted
import com.ssd.iagorawingman.utils.Other.dpToPx
import com.ssd.iagorawingman.utils.Other.reduceDragSensitivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProcessOrderContainerFragment : Fragment(R.layout.fragment_process_order) {

    private var _binding: FragmentProcessOrderBinding? = null
    private val binding get() = _binding as FragmentProcessOrderBinding

    private val viewModel: ProcessOrderViewModel by viewModel()
    private val confirmationViewModel: ConfirmationViewModel by sharedViewModel()
    private val confirmedViewModel: ConfirmedViewModel by sharedViewModel()
    private val paymentViewModel: PaymentViewModel by sharedViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentProcessOrderBinding.bind(view)

        handleTabViewPager()
        binding.initUI(requireActivity().navArgs<ProcessOrderActivityArgs>().value.positionTab)
    }



    private fun FragmentProcessOrderBinding.initUI(positionTab: Int) {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.initPositionTab.collect {
                    vpTabs.setCurrentItem(it ?: positionTab, true)
                }
            }
        }

        tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                viewModel.setInitPositionTab(tab.position)
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {/* NO-ACTION */
            }

            override fun onTabReselected(tab: TabLayout.Tab?) {/* NO-ACTION */
            }
        })
        vpTabs.reduceDragSensitivity()

    }

    private fun handleTabViewPager() {
        val tabTitle = this.resources.getStringArray(R.array.tab_title_order)
        val sectionsPagerAdapter =
            ProcessOrderTabLayoutAdapter(requireActivity() as AppCompatActivity)

        binding.apply {
            vpTabs.apply {
                adapter = sectionsPagerAdapter
                getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
            }



            TabLayoutMediator(tabs, vpTabs) { tab, position ->
                tab.text = tabTitle[position]
                val badgeDrawable = tab.setupBadgeDrawable()
                when (position) {
                    FlowProcessOrder.WAITING_CONFIRMATION.ordinal -> {
                        confirmationViewModel.vmCountSizeConfirmationList.setNumberBadge(
                            badgeDrawable, position
                        )

                    }
                    FlowProcessOrder.CONFIRMATION.ordinal -> {
                        confirmedViewModel.vmCountSizeConfirmedList.setNumberBadge(badgeDrawable,
                            position)
                    }

                    FlowProcessOrder.WAITING_PAYMENT.ordinal -> {
                        paymentViewModel.vmCountSizePaymentList.setNumberBadge(badgeDrawable,
                            position)
                    }
                }

            }.attach()

            tabs.apply {
                for (i in 0 until tabCount) {
                    val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
                    val p = tab.layoutParams as ViewGroup.MarginLayoutParams

                    tab.setPadding(
                        0,
                        0,
                        0,
                        0
                    )

                    p.setMargins(
                        dpToPx(10), // left
                        0, // top
                        dpToPx(5), // right
                        0)  // bottom
                    tab.requestLayout()

                }
            }
        }
    }

    private fun Flow<Int>.setNumberBadge(badgeDrawable: BadgeDrawable, pos: Int) {
        this.collectWhenStarted(this@ProcessOrderContainerFragment, { size ->
            badgeDrawable.number = size
            badgeDrawable.isVisible = size > 0

            if (size > 0) {
                binding.tabs.apply {
                    val tab = (getChildAt(0) as ViewGroup).getChildAt(pos)
                    tab.setPadding(0, 0, dpToPx(20), 0)
                    tab.requestLayout()
                }
            }
        })
    }

    private fun TabLayout.Tab.setupBadgeDrawable(): BadgeDrawable =
        this.orCreateBadge.apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_END
            horizontalOffset = dpToPx(-10)
            verticalOffset = dpToPx(10)
            backgroundColor =
                requireActivity().resources.getColor(R.color.redPrimary, null)

        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

}




