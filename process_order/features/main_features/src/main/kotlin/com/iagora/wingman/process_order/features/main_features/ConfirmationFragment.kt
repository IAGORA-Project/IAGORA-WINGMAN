package com.iagora.wingman.process_order.features.main_features

import androidx.navigation.fragment.findNavController
import com.iagora.wingman.commons.ui.extensions.collectWhenCreated
import com.iagora.wingman.process_order.features.waiting_list.WaitingListFragment
import com.iagora.wingman.process_order.viewmodels.ConfirmationViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel


class ConfirmationFragment :
    WaitingListFragment() {


    private val viewModel: ConfirmationViewModel by sharedViewModel()


    override fun setupDestinationToDetail() {
        adapter.setOnItemClickListener { idTransaction ->
            findNavController().navigate(
                ProcessOrderContainerFragmentDirections.moveToDetailConfirmation(idTransaction)
            )
        }
    }

    override fun subscribeToViewModel() {
        viewModel.vmGetConfirmationList.collectWhenCreated(this) { res ->
            binding.setupUI(res, adapter)
        }
    }

}