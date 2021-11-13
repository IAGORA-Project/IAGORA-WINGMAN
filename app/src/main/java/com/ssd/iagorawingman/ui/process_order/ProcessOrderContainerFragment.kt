package com.ssd.iagorawingman.ui.process_order

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentProcessOrderBinding
import com.ssd.iagorawingman.ui.main_menu.MainActivity
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProcessOrderContainerFragment : Fragment(R.layout.fragment_process_order) {

    private lateinit var binding: FragmentProcessOrderBinding
    private val viewModel: ProcessOrderViewModel by viewModel()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentProcessOrderBinding.bind(view)

        handleTabViewPager()
        initStart(requireActivity().navArgs<ProcessOrderActivityArgs>().value.positionTab)
    }


    private fun initStart(positionTab: Int) {
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
            }.attach()
        }
    }
}



