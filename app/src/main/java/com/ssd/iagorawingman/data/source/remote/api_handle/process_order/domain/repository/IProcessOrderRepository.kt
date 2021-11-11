package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository


import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow

interface IProcessOrderRepository {
    fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>>
    fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>>

    fun postBargainPrice(body: BargainBody): Flow<Resource<ProcessOrder.Global>>
    fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>>
}