package com.iagora.wingman.process_order.viewmodels

import androidx.lifecycle.viewModelScope
import com.iagora.wingman.commons.ui.base.BaseViewModel
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import com.iagora.wingman.process_order.helper.FlowProcessOrder
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmedDetailViewModel(
    private val processOrderUseCase: ProcessOrderUseCase,
) : BaseViewModel<ProcessOrder.DetailWaitingOnProcess>() {

    private val _vmGetDetailConfirmed: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmed =
        _vmGetDetailConfirmed.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }.buffer(1, BufferOverflow.DROP_OLDEST)

    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            processOrderUseCase.getDetailListWaiting(idTransaction,
                FlowProcessOrder.CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetDetailConfirmed.emit(res)
                }
        }
    }
}