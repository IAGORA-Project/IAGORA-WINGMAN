package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository.IProcessOrderRepository
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

class ProcessOrderOrderInteractor(
    private val iOrderRepository: IProcessOrderRepository
) : ProcessOrderUseCase {

    override fun getAllListWaiting(): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        iOrderRepository.getAllListWaiting()

    override fun getDetailListWaiting(idTransaction: String): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        iOrderRepository.getDetailListWaiting(idTransaction)

}