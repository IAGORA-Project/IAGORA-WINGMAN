package com.iagora.wingman.process_order.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import com.iagora.wingman.helper.FlowProcessOrder
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmationViewModel(
    private val processOrderUseCase: ProcessOrderUseCase,
) : ViewModel() {

    private val _vmGetConfirmationList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmGetConfirmationList = _vmGetConfirmationList.distinctUntilChanged()

    private val _vmCountSizeConfirmationList: MutableSharedFlow<Int> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmCountSizeConfirmationList = _vmCountSizeConfirmationList.distinctUntilChanged()

    init {
        initViewModelConfirmation()
    }

    fun initViewModelConfirmation() {
        viewModelScope.launch {
            processOrderUseCase.getAllListWaiting(FlowProcessOrder.WAITING_CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmationList.emit(res)
                    _vmCountSizeConfirmationList.emit(res.data?.success?.size ?: 0)
                }
        }

    }
}