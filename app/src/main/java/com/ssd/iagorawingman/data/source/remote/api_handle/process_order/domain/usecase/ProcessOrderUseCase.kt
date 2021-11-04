package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.usecase

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow


interface ProcessOrderUseCase {
    fun getAllListWaiting(): Flow<Resource<ProcessOrder.ListWaitingOnProcess>>
    fun getDetailListWaiting(idTransaction: String): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>>
}