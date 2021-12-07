package com.iagora.wingman.process_order.features.main_features

import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.navArgs
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.badge.BadgeDrawable
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.iagora.wingman.commons.ui.base.BaseFragment
import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.commons.ui.extensions.collectWithDelay
import com.iagora.wingman.commons.views.helper.Util.dpToPx
import com.iagora.wingman.commons.views.helper.Util.reduceDragSensitivity
import com.iagora.wingman.process_order.features.main_features.databinding.FragmentProcessOrderBinding
import com.iagora.wingman.process_order.helper.FlowProcessOrder
import com.iagora.wingman.process_order.viewmodels.ConfirmationViewModel
import com.iagora.wingman.process_order.viewmodels.ConfirmedViewModel
import com.iagora.wingman.process_order.viewmodels.PaymentViewModel
import com.iagora.wingman.process_order.viewmodels.ProcessOrderViewModel
import kotlinx.coroutines.flow.Flow
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ProcessOrderContainerFragment :
    BaseFragment<FragmentProcessOrderBinding>(R.layout.fragment_process_order,
        { FragmentProcessOrderBinding.bind(it) }) {

    private val confirmationViewModel: ConfirmationViewModel by sharedViewModel()
    private val confirmedViewModel: ConfirmedViewModel by sharedViewModel()
    private val paymentViewModel: PaymentViewModel by sharedViewModel()
    private val processOrderViewModel: ProcessOrderViewModel by sharedViewModel()

    override fun setView() {
        with(binding) {
            vpTabs.setup()
            tabs.setup(vpTabs)
        }
    }


    private fun TabLayout.setup(vpTabs: ViewPager2) {
        val tabTitle =
            this@ProcessOrderContainerFragment.resources.getStringArray(R.array.tab_title_order)

        TabLayoutMediator(this, vpTabs) { tab, position ->
            tab.text = tabTitle[position]
            val badgeDrawable = tab.setupBadgeDrawable()
            when (position) {
                FlowProcessOrder.WAITING_CONFIRMATION.ordinal -> {
                    confirmationViewModel.vmCountSizeConfirmationList.setNumberBadge(
                        badgeDrawable, position
                    )

                }
                FlowProcessOrder.CONFIRMATION.ordinal -> {
                    confirmedViewModel.vmCountSizeConfirmedList.setNumberBadge(badgeDrawable,
                        position)
                }

                FlowProcessOrder.WAITING_PAYMENT.ordinal -> {
                    paymentViewModel.vmCountSizePaymentList.setNumberBadge(badgeDrawable,
                        position)
                }
            }

        }.attach()

        for (i in 0 until tabCount) {
            val tab = (getChildAt(0) as ViewGroup).getChildAt(i)
            val p = tab.layoutParams as ViewGroup.MarginLayoutParams

            tab.setPadding(
                0,
                0,
                0,
                0
            )

            p.setMargins(
                dpToPx(10), // left
                0, // top
                dpToPx(5), // right
                0)  // bottom
            tab.requestLayout()

        }

    }

    private fun ViewPager2.setup() {
        val positionTab = requireActivity().navArgs<ProcessOrderActivityArgs>().value.positionTab
        val sectionsPagerAdapter =
            ProcessOrderTabLayoutAdapter(requireActivity() as AppCompatActivity)

        adapter = sectionsPagerAdapter

        getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
        reduceDragSensitivity()

        processOrderViewModel.posTab.collectWithDelay(this@ProcessOrderContainerFragment,
            10) {
            setCurrentItem(if (it == 0) positionTab else it, true)
        }
    }

    private fun Flow<Int>.setNumberBadge(badgeDrawable: BadgeDrawable, pos: Int) {
        collectWhenStarted(this@ProcessOrderContainerFragment, { size ->
            badgeDrawable.number = size
            badgeDrawable.isVisible = size > 0

            if (size > 0) {
                binding.tabs.apply {
                    val tab = (getChildAt(0) as ViewGroup).getChildAt(pos)
                    tab.setPadding(0, 0, dpToPx(20), 0)
                    tab.requestLayout()
                }
            }
        })
    }

    private fun TabLayout.Tab.setupBadgeDrawable(): BadgeDrawable =
        orCreateBadge.apply {
            isVisible = false
            badgeGravity = BadgeDrawable.TOP_END
            horizontalOffset = dpToPx(-10)
            verticalOffset = dpToPx(10)
            backgroundColor =
                requireActivity().resources.getColor(R.color.redPrimary, null)

        }


}




