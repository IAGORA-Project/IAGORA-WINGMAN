package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.WaitingListFragment

class OnProcessPagerAdapter(
    private val activity: AppCompatActivity
) : FragmentStateAdapter(activity) {

    private val pageType = activity.resources.getStringArray(R.array.path_response_waiting_list)

    override fun getItemCount(): Int = pageType.size

    override fun createFragment(pos: Int): Fragment {

        val fragment = WaitingListFragment()
        fragment.arguments = Bundle().apply {
            putString(WaitingListFragment.PAGE_TYPE, pageType[pos])
        }

        return fragment
    }
}