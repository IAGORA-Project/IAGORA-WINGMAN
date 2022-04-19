package com.iagora.wingman.process_order.features.paid

import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.process_order.features.paid.databinding.FragmentPaidDeiliveryTypeBinding

class DeliveryTypeFragment :
    BaseFragment<FragmentPaidDeiliveryTypeBinding>(R.layout.fragment_paid_deilivery_type,
        { FragmentPaidDeiliveryTypeBinding.bind(it) }) {

    override fun setView() {

    }

    companion object {
        const val TYPE_DELIVERY = "delivery by ?"
    }

}