package com.ssd.iagorawingman.ui.process_order.waiting_list.paid

import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentPaidBinding
import com.ssd.iagorawingman.utils.Other.dpToPx

class PaidFragment : Fragment(R.layout.fragment_paid) {
    private var _binding: FragmentPaidBinding? = null
    private val binding get() = _binding as FragmentPaidBinding
    private lateinit var pagerAdapter: PaidPagerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaidBinding.bind(view)

        handleTabLayout()
    }

    private fun handleTabLayout() {
        val tabTitle = requireActivity().resources.getStringArray(R.array.tab_title_paid)
        pagerAdapter = PaidPagerAdapter(requireActivity() as AppCompatActivity)

        binding.apply {
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
                    p.setMargins(
                        dpToPx(15), // left
                        dpToPx(20), // top
                        dpToPx(5), // right
                        dpToPx(5))  // bottom
                    tab.requestLayout()
                }
            }

        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}