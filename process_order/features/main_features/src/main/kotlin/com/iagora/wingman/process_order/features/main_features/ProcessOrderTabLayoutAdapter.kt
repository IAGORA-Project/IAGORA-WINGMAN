package com.iagora.wingman.process_order.features.main_features

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.iagora.wingman.process_order.features.finished.FinishedFragment
import com.iagora.wingman.process_order.features.paid.PaidFragment
import com.iagora.wingman.process_order.features.payment.PaymentFragment
import com.iagora.wingman.process_order.features.sent.SentFragment

class ProcessOrderTabLayoutAdapter(
    activity: AppCompatActivity,
) : FragmentStateAdapter(activity) {

    private val pageType = activity.resources.getStringArray(R.array.tab_title_order)

    override fun getItemCount(): Int =
      pageType.size


    override fun createFragment(pos: Int): Fragment {

        var fragment: Fragment? = null
            when (pos) {
                0 -> fragment =
                    ConfirmationFragment()
                1 -> fragment =
                    ConfirmedFragment()
                2 -> fragment = PaymentFragment()
                3 -> fragment = PaidFragment()
                4 -> fragment = SentFragment()
                5 -> fragment =
                    FinishedFragment()
            }
        return fragment as Fragment

    }
}