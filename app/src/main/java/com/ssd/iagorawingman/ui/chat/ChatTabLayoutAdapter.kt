package com.ssd.iagorawingman.ui.chat

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class ChatTabLayoutAdapter(
    fragment: FragmentActivity, var listFragment:MutableList<Fragment>
): FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int = listFragment.size

    override fun createFragment(position: Int): Fragment {
        return listFragment[position]
    }
}