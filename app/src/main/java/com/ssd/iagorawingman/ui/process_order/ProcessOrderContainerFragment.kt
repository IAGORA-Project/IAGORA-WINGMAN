package com.ssd.iagorawingman.ui.process_order

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.badge.BadgeDrawable.TOP_END
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentProcessOrderBinding
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.ConfirmationViewModel
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.ConfirmedViewModel
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Other.reduceDragSensitivity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.sharedStateViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProcessOrderContainerFragment : Fragment(R.layout.fragment_process_order) {

    private lateinit var binding: FragmentProcessOrderBinding
    private val viewModel: ProcessOrderViewModel by viewModel()
    private val confirmationViewModel: ConfirmationViewModel by sharedStateViewModel()
    private val confirmedViewModel: ConfirmedViewModel by sharedStateViewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProcessOrderBinding.bind(view)

        initData()
        handleTabViewPager()
        initUI(requireActivity().navArgs<ProcessOrderActivityArgs>().value.positionTab)
    }

    private fun initData() {
        confirmedViewModel.initViewModelConfirmed()
        confirmationViewModel.initViewModelConfirmation()
    }


    private fun initUI(positionTab: Int) {
        binding.apply {
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
                            badgeDrawable
                        )
                    }
                    FlowProcessOrder.CONFIRMATION.ordinal -> {
                        confirmedViewModel.vmCountSizeConfirmedList.setNumberBadge(badgeDrawable)
                    }
                }

            }.attach()
        }
    }

    private fun Flow<Int>.setNumberBadge(badgeDrawable: BadgeDrawable) {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                this@setNumberBadge.collectLatest { size ->
                    badgeDrawable.number = size
                    badgeDrawable.isVisible = size > 0
                }
            }
        }
    }

    private fun TabLayout.Tab.setupBadgeDrawable(): BadgeDrawable =
        this.orCreateBadge.apply {
            isVisible = false
            badgeGravity = TOP_END
            backgroundColor =
                requireActivity().resources.getColor(R.color.redPrimary, null)

        }

}




