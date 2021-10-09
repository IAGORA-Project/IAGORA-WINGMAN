package com.ssd.iagorawingman.ui.process_order

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ProcessOrderTabLayoutAdapter(
    fragment: FragmentActivity, var listFragment:MutableList<Fragment>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}