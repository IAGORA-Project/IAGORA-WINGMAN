package com.ssd.iagorawingman.ui.process_order.waiting_list

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.databinding.FragmentOnProcessWaitingListBinding
import com.ssd.iagorawingman.utils.Other.hide
import com.ssd.iagorawingman.utils.Other.show
import com.ssd.iagorawingman.utils.Resource
import com.ssd.iagorawingman.utils.Status

object WaitingList {
    fun FragmentOnProcessWaitingListBinding.handleUI(
        res: Resource<ProcessOrder.ListWaitingOnProcess>,
        adapter: WaitingListAdapter,
    ) {
        res.apply {
            when (res.status) {
                Status.LOADING -> {
                    rvListOrder.hide().run {
                        containerLoadingListOrder.root.show()
                    }
                }
                Status.SUCCESS -> {
                    adapter.differ.submitList(res.data?.success).run {
                        containerLoadingListOrder.root.hide().run {
                            rvListOrder.show()
                        }
                    }
                }

                Status.ERROR -> {
                    containerLoadingListOrder.root.hide()
                    rvListOrder.hide()
                }
            }
        }

    }
}