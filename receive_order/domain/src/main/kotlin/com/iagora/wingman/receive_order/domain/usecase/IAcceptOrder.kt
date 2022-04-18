package com.iagora.wingman.receive_order.domain.usecase

import com.iagora.wingman.core.util.SimpleResource
import com.iagora.wingman.receive_order.domain.models.body.ReceiveOrder

interface IAcceptOrder {
    suspend operator fun invoke(
        request: ReceiveOrder
    ): SimpleResource
}