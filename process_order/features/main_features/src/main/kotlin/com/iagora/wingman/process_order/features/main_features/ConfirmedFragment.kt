package com.iagora.wingman.process_order.features.main_features

import androidx.navigation.fragment.findNavController
import com.iagora.wingman.core.presentation.extensions.collectWhenStarted
import com.iagora.wingman.process_order.features.waiting_list.WaitingListFragment
import com.iagora.wingman.process_order.viewmodels.ConfirmedViewModel
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ConfirmedFragment : WaitingListFragment() {

    private val viewModel: ConfirmedViewModel by sharedViewModel()


    override fun setupDestinationToDetail() {
        adapter.setOnItemClickListener { idTransaction ->
            findNavController().navigate(
                ProcessOrderContainerFragmentDirections.moveToDetailConfirmed(idTransaction)
            )
        }
    }

    override fun subscribeToViewModel() {
        viewModel.vmGetConfirmedList.collectWhenStarted(viewLifecycleOwner) { res ->
            binding.setupUI(res, adapter)
        }
    }


}