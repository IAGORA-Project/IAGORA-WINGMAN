package com.ssd.iagorawingman.ui.process_order.waiting_list.confirmation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase.ProcessOrderUseCase
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.body.HandlingFeeBody
import com.ssd.iagorawingman.utils.FlowProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.channels.BufferOverflow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.buffer
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmationDetailViewModel(
    private val orderUseCase: ProcessOrderUseCase,
) : ViewModel() {


    private val _vmGetDetailConfirmation: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmation =
        _vmGetDetailConfirmation.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }.buffer(1, BufferOverflow.DROP_OLDEST)


    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            orderUseCase.getDetailListWaiting(idTransaction,
                FlowProcessOrder.WAITING_CONFIRMATION.name).collectLatest {
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


    private val _vmGetFeedBackChangeHandlingFee: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()

    val vmGetFeedBackChangeHandlingFee = _vmGetFeedBackChangeHandlingFee.distinctUntilChanged()

    fun sendNewHandlingFee(idTransaction: String, handlingFeeBody: HandlingFeeBody) =
        viewModelScope.launch {
            orderUseCase.postNewHandlingFee(idTransaction, handlingFeeBody).collectLatest { fed ->
                _vmGetFeedBackChangeHandlingFee.emit(fed)
            }
        }


}