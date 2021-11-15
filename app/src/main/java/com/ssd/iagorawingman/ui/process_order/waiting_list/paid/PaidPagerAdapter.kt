package com.ssd.iagorawingman.ui.process_order.waiting_list.paid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssd.iagorawingman.R

class PaidPagerAdapter(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    private val pageType = activity.resources.getStringArray(R.array.tab_title_paid)

    override fun getItemCount(): Int = pageType.size

    override fun createFragment(pos: Int): Fragment {

        val fragment = DeliveryTypeFragment()
        fragment.arguments = Bundle().apply {
            putString(DeliveryTypeFragment.TYPE_DELIVERY, pageType[pos])
        }

        return fragment
    }
}