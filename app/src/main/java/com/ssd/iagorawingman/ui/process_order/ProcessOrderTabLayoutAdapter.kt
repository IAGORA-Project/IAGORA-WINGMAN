package com.ssd.iagorawingman.ui.process_order

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.ui.process_order.finished.FinishedFragment
import com.ssd.iagorawingman.ui.process_order.waiting_list.paid.PaidFragment
import com.ssd.iagorawingman.ui.process_order.sent.SentFragment
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.ConfirmationFragment
import com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed.ConfirmedFragment
import com.ssd.iagorawingman.ui.process_order.waiting_list.payment.PaymentFragment

class ProcessOrderTabLayoutAdapter(
    activity: AppCompatActivity,
    private val listFragment: List<Fragment>? = null,
) : FragmentStateAdapter(activity) {

    private val pageType = activity.resources.getStringArray(R.array.tab_title_order)

    override fun getItemCount(): Int =
        listFragment?.size ?: pageType.size


    override fun createFragment(pos: Int): Fragment {
        return if (listFragment != null) {
            listFragment[pos]
        } else {
            var fragment: Fragment? = null
            when (pos) {
                0 -> fragment = ConfirmationFragment()
                1 -> fragment = ConfirmedFragment()
                2 -> fragment = PaymentFragment()
                3 -> fragment = PaidFragment()
                4 -> fragment = SentFragment()
                5 -> fragment = FinishedFragment()
            }
            fragment as Fragment
        }
    }
}