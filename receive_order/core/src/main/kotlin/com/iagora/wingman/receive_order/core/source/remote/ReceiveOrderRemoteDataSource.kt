package com.iagora.wingman.receive_order.core.source.remote

import com.iagora.wingman.core.util.flowCollector
import com.iagora.wingman.receive_order.core.ReceiveOrderApi
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import kotlinx.coroutines.CoroutineScope

class ReceiveOrderRemoteDataSource(
    private val services: ReceiveOrderApi,
    private val externalScope: CoroutineScope,
) {
   suspend fun postActionOrder(
        action: String,
        receiveOrderBody: ReceiveOrderBody,
    ) = services.postActionOrder(action, receiveOrderBody).flowCollector(externalScope)

}