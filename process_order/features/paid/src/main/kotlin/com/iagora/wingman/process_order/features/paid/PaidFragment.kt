package com.iagora.wingman.process_order.features.paid

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.views.helper.Util.dpToPx
import com.iagora.wingman.process_order.features.paid.databinding.FragmentPaidBinding

class PaidFragment :
    BaseFragment<FragmentPaidBinding>(R.layout.fragment_paid, { FragmentPaidBinding.bind(it) }) {
    private lateinit var pagerAdapter: PaidPagerAdapter

    override fun setView() {
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

}