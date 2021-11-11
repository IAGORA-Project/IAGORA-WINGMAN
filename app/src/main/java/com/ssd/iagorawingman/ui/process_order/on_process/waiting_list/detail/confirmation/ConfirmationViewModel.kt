package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail.confirmation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.utils.Constants.WAITING_CONFIRMATION
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmationViewModel(
    private val orderUseCase: ProcessOrderUseCase
) : ViewModel() {


    private val _vmGetDetailConfirmation: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmation =
        _vmGetDetailConfirmation.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }.buffer(1, BufferOverflow.DROP_OLDEST)


    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            orderUseCase.getDetailListWaiting(idTransaction, WAITING_CONFIRMATION).collectLatest {
                _vmGetDetailConfirmation.emit(it)
            }
        }
    }

    private val _vmGetFeedBackBargainPrice: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()
    val vmGetFeedBackBargainPrice = _vmGetFeedBackBargainPrice.distinctUntilChanged()
    fun sendBargainPrice(body: BargainBody) {
        viewModelScope.launch {
            orderUseCase.postBargainPrice(body).collectLatest { fed ->
                _vmGetFeedBackBargainPrice.emit(fed)
            }
        }
    }

    private val _vmGetFeedBackActionTransaction: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()

    val vmGetFeedBackActionTransaction = _vmGetFeedBackActionTransaction.distinctUntilChanged()

    fun sendActionTransaction(idTransaction: String, typeAction: String) {
        viewModelScope.launch {
            orderUseCase.postActionTransaction(idTransaction, typeAction).collectLatest { fed ->
                _vmGetFeedBackActionTransaction.emit(fed)
            }
        }
    }
}