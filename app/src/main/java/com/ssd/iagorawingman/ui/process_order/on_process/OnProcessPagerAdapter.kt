package com.ssd.iagorawingman.ui.process_order.on_process

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.WaitingListFragment

class OnProcessPagerAdapter(
    activity: AppCompatActivity
) : FragmentStateAdapter(activity) {
    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {
        val fragment = WaitingListFragment()
        fragment.arguments = Bundle().apply {
            putInt(WaitingListFragment.PAGE_TYPE, position)
        }

        return fragment
    }
}