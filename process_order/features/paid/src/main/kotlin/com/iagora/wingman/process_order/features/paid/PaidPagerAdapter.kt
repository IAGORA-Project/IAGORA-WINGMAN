package com.iagora.wingman.process_order.features.paid

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter

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