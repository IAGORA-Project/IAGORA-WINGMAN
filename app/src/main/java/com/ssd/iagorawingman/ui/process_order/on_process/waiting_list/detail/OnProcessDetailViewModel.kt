package com.ssd.iagorawingman.ui.process_order.on_process.waiting_list.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.collectLatest
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

    private val _vmGetFeedBackBargainPrice: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()
    val vmGetFeedBackBargainPrice = _vmGetFeedBackBargainPrice.asSharedFlow()
    fun sendBargainPrice(body: BargainBody) {
        viewModelScope.launch {
            orderUseCase.postBargainPrice(body).collectLatest { fed ->
                _vmGetFeedBackBargainPrice.emit(fed)
            }
        }
    }

    private val _vmGetFeedBackActionTransaction: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()

    val vmGetFeedBackActionTransaction = _vmGetFeedBackActionTransaction.asSharedFlow()

    fun sendActionTransaction(idTransaction: String, typeAction: String) {
        viewModelScope.launch {
            orderUseCase.postActionTransaction(idTransaction, typeAction).collectLatest { fed ->
                _vmGetFeedBackBargainPrice.emit(fed)
            }
        }
    }
}