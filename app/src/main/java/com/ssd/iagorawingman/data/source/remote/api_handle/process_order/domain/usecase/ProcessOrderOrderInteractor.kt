package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository.IProcessOrderRepository
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

class ProcessOrderOrderInteractor(
    private val iOrderRepository: IProcessOrderRepository
) : ProcessOrderUseCase {

    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        iOrderRepository.getAllListWaiting(typeWaiting)

    override fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        iOrderRepository.getDetailListWaiting(idTransaction, typeWaiting)

    override fun postActionTransaction(
        idTransaction: String,
        typeAction: String
    ): Flow<Resource<ProcessOrder.Global>> =
        iOrderRepository.postActionTransaction(idTransaction, typeAction)

    override fun postBargainPrice(body: BargainBody): Flow<Resource<ProcessOrder.Global>> =
        iOrderRepository.postBargainPrice(body)

}