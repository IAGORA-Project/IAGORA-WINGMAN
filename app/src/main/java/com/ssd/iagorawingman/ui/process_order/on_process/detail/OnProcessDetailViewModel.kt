package com.ssd.iagorawingman.ui.process_order.on_process.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class OnProcessDetailViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {

    private val _vmGetDetailWaitingOnProcess: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailWaitingOnProcess = _vmGetDetailWaitingOnProcess.asSharedFlow()


    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            orderUseCase.getDetailListWaiting(idTransaction).collectLatest {
                _vmGetDetailWaitingOnProcess.emit(it)
            }
        }
    }

}