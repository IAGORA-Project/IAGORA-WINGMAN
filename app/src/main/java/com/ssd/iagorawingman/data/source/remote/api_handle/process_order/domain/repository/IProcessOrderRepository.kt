package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository


import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IProcessOrderRepository {
    fun getAllListWaiting(): Flow<Resource<ProcessOrder.ListWaitingOnProcess>>
    fun getDetailListWaiting(idTransaction: String): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>>
}