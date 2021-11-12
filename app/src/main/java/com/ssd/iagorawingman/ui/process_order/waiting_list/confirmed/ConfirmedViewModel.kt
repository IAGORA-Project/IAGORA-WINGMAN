package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmed

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmedViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {
    private val _vmGetConfirmedList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetConfirmedList = _vmGetConfirmedList.distinctUntilChanged()

    fun initViewModelConfirmed() {
        viewModelScope.launch {
            orderUseCase.getAllListWaiting(FlowProcessOrder.CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmedList.emit(res)
                }
        }

    }
}