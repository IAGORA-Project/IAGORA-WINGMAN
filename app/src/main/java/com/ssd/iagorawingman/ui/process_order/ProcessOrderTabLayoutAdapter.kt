package com.ssd.iagorawingman.ui.process_order

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.ui.process_order.finished.FinishedFragment
import com.ssd.iagorawingman.ui.process_order.on_process.OnProcessFragment
import com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.WaitingListFragment
import com.ssd.iagorawingman.ui.process_order.paid.PaidFragment
import com.ssd.iagorawingman.ui.process_order.sent.SentFragment

class ProcessOrderTabLayoutAdapter(
    private val activity: AppCompatActivity,
    private val listFragment: List<Fragment>? = null
) : FragmentStateAdapter(activity) {

    override fun getItemCount(): Int =
        listFragment?.size ?: activity.resources.getStringArray(R.array.tab_title_order).size


    override fun createFragment(position: Int): Fragment {
        return if (listFragment != null) {
            listFragment[position]
        } else {
            var fragment: Fragment? = null
            when (position) {
                0 -> fragment = OnProcessFragment()
                1 -> fragment = PaidFragment()
                2 -> fragment = SentFragment()
                3 -> fragment = FinishedFragment()
            }
            fragment as Fragment
        }
    }
}