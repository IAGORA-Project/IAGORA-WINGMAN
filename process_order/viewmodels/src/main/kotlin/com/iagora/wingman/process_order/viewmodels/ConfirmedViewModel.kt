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

class ConfirmedViewModel(
    private val processOrderUseCase: ProcessOrderUseCase,
) : ViewModel() {


    private val _vmGetConfirmedList: MutableSharedFlow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmGetConfirmedList = _vmGetConfirmedList.distinctUntilChanged()

    private val _vmCountSizeConfirmedList: MutableSharedFlow<Int> =
        MutableSharedFlow(1, 0, BufferOverflow.DROP_OLDEST)
    val vmCountSizeConfirmedList = _vmCountSizeConfirmedList.distinctUntilChanged()

    init {
        initViewModelConfirmed()
    }


    fun initViewModelConfirmed() {
        viewModelScope.launch {
            processOrderUseCase.getAllListWaiting(FlowProcessOrder.CONFIRMATION.name)
                .collectLatest { res ->
                    _vmGetConfirmedList.emit(res)
                    _vmCountSizeConfirmedList.emit(res.data?.success?.size ?: 0)
                }
        }
    }
}