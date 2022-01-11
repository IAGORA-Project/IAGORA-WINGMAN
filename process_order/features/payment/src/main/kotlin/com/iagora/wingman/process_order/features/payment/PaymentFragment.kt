package com.iagora.wingman.process_order.features.payment

import com.iagora.wingman.commons.ui.extensions.collectWhenStarted
import com.iagora.wingman.process_order.features.waiting_list.WaitingListFragment
import com.iagora.wingman.process_order.viewmodels.PaymentViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class PaymentFragment :
    WaitingListFragment() {


    private val viewModel: PaymentViewModel by sharedViewModel()


    override fun setupDestinationToDetail() {
        adapter.setOnItemClickListener { idTransaction ->

        }
    }

    override fun subscribeToViewModel() {
        viewModel.vmGetPaymentList.collectWhenStarted(viewLifecycleOwner) { res ->
            binding.setupUI(res, adapter)
        }
    }

}