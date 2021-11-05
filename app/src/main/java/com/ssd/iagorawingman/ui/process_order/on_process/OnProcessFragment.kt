package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentOnProcessBinding
import com.ssd.iagorawingman.utils.Other

class OnProcessFragment : Fragment(R.layout.fragment_on_process) {
    private var _binding: FragmentOnProcessBinding? = null
    private val binding get() = _binding
    private lateinit var pagerAdapter: OnProcessPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentOnProcessBinding.bind(view)

        handleTabLayout()
    }

    private fun handleTabLayout() {
        val tabTitle = requireActivity().resources.getStringArray(R.array.tab_title_waiting_list)
        pagerAdapter = OnProcessPagerAdapter(requireActivity() as AppCompatActivity)

        binding?.apply {
            vpOnProcess.apply {
                adapter = pagerAdapter
                isUserInputEnabled = false
            }

            TabLayoutMediator(tabOnProcess, vpOnProcess) { tab, pos ->
                tab.text = tabTitle[pos]
            }.attach()


            tabOnProcess.apply {
                addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
                    override fun onTabReselected(tab: TabLayout.Tab?) {/*NO-ACTION*/
                    }

                    override fun onTabUnselected(tab: TabLayout.Tab?) {/*NO-ACTION*/
                    }

                    override fun onTabSelected(tab: TabLayout.Tab?) {
                        tab?.position?.let { vpOnProcess.setCurrentItem(it, false) }
                    }
                })


                for (i in 0 until tabCount) {
                    val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
                    val p = tab.layoutParams as ViewGroup.MarginLayoutParams
                    p.setMargins(Other.dpToPx(0), 0, Other.dpToPx(8), 0)
                    tab.requestLayout()
                }
            }

        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}