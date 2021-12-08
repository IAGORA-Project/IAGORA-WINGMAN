package com.iagora.wingman.receive_order.core.source.remote

import com.iagora.wingman.core.source.remote.network.flowCollector
import com.iagora.wingman.receive_order.core.ReceiveOrderApi
import com.iagora.wingman.receive_order.helper.data.remote.body.ReceiveOrderBody
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