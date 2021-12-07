package com.iagora.wingman.receive_order.core.domain.usecase

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.receive_order.core.domain.repository.IReceiveOrderRepository
import com.iagora.wingman.receive_order.core.model.body.ReceiveOrder
import com.iagora.wingman.receive_order.core.model.response.AcceptedOrder
import kotlinx.coroutines.flow.Flow

class ReceiveOrderInteractor(private val iReceiveOrderRepository: IReceiveOrderRepository) :
    ReceiveOrderUseCase {
    override fun postActionOrder(
        action: String,
        receiveOrder: ReceiveOrder,
    ): Flow<Resource<AcceptedOrder>> = iReceiveOrderRepository.postActionOrder(action, receiveOrder)
}