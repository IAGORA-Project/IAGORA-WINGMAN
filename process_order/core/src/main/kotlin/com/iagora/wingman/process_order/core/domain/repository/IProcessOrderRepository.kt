package com.iagora.wingman.process_order.core.domain.repository

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.flow.Flow


interface IProcessOrderRepository {
    fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>>
    fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String,
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>>

    fun postBargainPrice(body: Bargain): Flow<Resource<ProcessOrder.Global>>
    fun postNewHandlingFee(
        idTransaction: String, handlingFee: HandlingFee,
    ): Flow<Resource<ProcessOrder.Global>>

    fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>>
}