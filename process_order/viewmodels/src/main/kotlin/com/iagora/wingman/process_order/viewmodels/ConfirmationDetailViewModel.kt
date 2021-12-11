package com.iagora.wingman.process_order.viewmodels

import androidx.lifecycle.viewModelScope
import com.iagora.wingman.commons.ui.base.BaseViewModel
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.usecase.ProcessOrderUseCase
import com.iagora.wingman.helper.FlowProcessOrder
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch

class ConfirmationDetailViewModel(
    private val processOrderUseCase: ProcessOrderUseCase,
) : BaseViewModel<ProcessOrder.DetailWaitingOnProcess>() {


    private val _vmGetDetailConfirmation: MutableSharedFlow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        MutableSharedFlow()
    val vmGetDetailConfirmation =
        _vmGetDetailConfirmation.distinctUntilChanged { old, new ->
            old.data?.success?.idTransaction != new.data?.success?.idTransaction
        }


    fun setIdTransaction(idTransaction: String) {
        viewModelScope.launch {
            processOrderUseCase.getDetailListWaiting(idTransaction,
                FlowProcessOrder.WAITING_CONFIRMATION.name)
                .collectLatest {
                    _vmGetDetailConfirmation.emit(it)
                }
        }
    }

    private val _vmGetFeedBackBargainPrice: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()
    val vmGetFeedBackBargainPrice = _vmGetFeedBackBargainPrice.distinctUntilChanged()
    fun sendBargainPrice(body: Bargain) {
        viewModelScope.launch {
            processOrderUseCase.postBargainPrice(body).collectLatest { fed ->
                _vmGetFeedBackBargainPrice.emit(fed)
            }
        }
    }

    private val _vmGetFeedBackActionTransaction: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()

    val vmGetFeedBackActionTransaction = _vmGetFeedBackActionTransaction.distinctUntilChanged()

    fun sendActionTransaction(idTransaction: String, typeAction: String) {
        viewModelScope.launch {
            processOrderUseCase.postActionTransaction(idTransaction, typeAction).collectLatest { fed ->
                _vmGetFeedBackActionTransaction.emit(fed)
            }
        }
    }


    private val _vmGetFeedBackChangeHandlingFee: MutableSharedFlow<Resource<ProcessOrder.Global>> =
        MutableSharedFlow()

    val vmGetFeedBackChangeHandlingFee = _vmGetFeedBackChangeHandlingFee.distinctUntilChanged()

    fun sendNewHandlingFee(
        idTransaction: String,
        handlingFee: HandlingFee,
    ) =
        viewModelScope.launch {
            processOrderUseCase.postNewHandlingFee(idTransaction, handlingFee).collectLatest { fed ->
                _vmGetFeedBackChangeHandlingFee.emit(fed)
            }
        }
}