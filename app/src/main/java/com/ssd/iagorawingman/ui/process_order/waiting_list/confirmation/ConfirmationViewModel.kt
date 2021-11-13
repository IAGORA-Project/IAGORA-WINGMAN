package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation

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

class ConfirmationViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetConfirmationList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetConfirmationList = _vmGetConfirmationList.distinctUntilChanged()

    fun initViewModelConfirmation() {
        viewModelScope.launch {
            orderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmationList.emit(res)
                }
        }

    }
}