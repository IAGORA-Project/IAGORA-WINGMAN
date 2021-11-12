package com.ssd.iagorawingman.ui.process_order.paid

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import com.ssd.iagorawingman.R
import com.ssd.iagorawingman.databinding.FragmentPaidDeiliveryTypeBinding

class DeliveryTypeFragment : Fragment(R.layout.fragment_paid_deilivery_type) {

    private var _binding: FragmentPaidDeiliveryTypeBinding? = null
    private val binding get() = _binding as FragmentPaidDeiliveryTypeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentPaidDeiliveryTypeBinding.bind(view)
    }

    companion object {
        const val TYPE_DELIVERY = "delivery by ?"
    }
}