package com.iagora.wingman.process_order.features.waiting_list


import com.iagora.wingman.core.presentation.base.BaseFragment
import com.iagora.wingman.core.presentation.util.Util.hide
import com.iagora.wingman.core.presentation.util.Util.show
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.helper.set
import com.iagora.wingman.process_order.features.waiting_list.databinding.FragmentWaitingListBinding
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder


abstract class WaitingListFragment :
    BaseFragment<FragmentWaitingListBinding>(R.layout.fragment_waiting_list,
        ({ FragmentWaitingListBinding.bind(it) })) {


    private var _adapter: WaitingListAdapter? = null
    protected val adapter get() = _adapter as WaitingListAdapter

    override fun setView() {
        setupAdapter()
        subscribeToViewModel()
        setupDestinationToDetail()
    }


    abstract fun setupDestinationToDetail()
    abstract fun subscribeToViewModel()

    private fun setupAdapter() {
        _adapter = WaitingListAdapter()
        binding.rvListOrder.adapter = adapter
    }

    protected fun FragmentWaitingListBinding.setupUI(
        res: Resource<ProcessOrder.ListWaitingOnProcess>,
        adapter: WaitingListAdapter,
    ) {
        res.apply {
            set(
                success = {
                    adapter.submitList(data?.success).run {
                        containerLoadingListOrder.root.hide().also {
                            rvListOrder.show()
                        }
                    }
                },
                error = {
                    containerLoadingListOrder.root.hide().run {
                        rvListOrder.hide()
                    }
                },
                loading = {
                    rvListOrder.hide().run {
                        containerLoadingListOrder.root.show()
                    }
                },
            )

        }

    }


    override fun onDestroyView() {
        _adapter = null
        super.onDestroyView()
    }

}