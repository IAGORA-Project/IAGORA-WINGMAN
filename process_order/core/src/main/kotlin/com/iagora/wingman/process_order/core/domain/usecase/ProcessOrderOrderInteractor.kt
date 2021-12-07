package com.iagora.wingman.process_order.core.domain.usecase

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.domain.repository.IProcessOrderRepository
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.flow.Flow

class ProcessOrderOrderInteractor(
    private val iProcessOrderRepository: IProcessOrderRepository,
) : ProcessOrderUseCase {

    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        iProcessOrderRepository.getAllListWaiting(typeWaiting)

    override fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String,
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        iProcessOrderRepository.getDetailListWaiting(idTransaction, typeWaiting)

    override fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>> =
        iProcessOrderRepository.postActionTransaction(idTransaction, typeAction)

    override fun postBargainPrice(body: Bargain): Flow<Resource<ProcessOrder.Global>> =
        iProcessOrderRepository.postBargainPrice(body)

    override fun postNewHandlingFee(
        idTransaction: String,
        handlingFee: HandlingFee,
    ): Flow<Resource<ProcessOrder.Global>> =
        iProcessOrderRepository.postNewHandlingFee(idTransaction, handlingFee)

}