package com.iagora.wingman.receive_order.core.data.remote

import com.iagora.wingman.core.source.remote.network.flowCollector
import com.iagora.wingman.receive_order.core.ReceiveOrderApi
import com.iagora.wingman.receive_order.core.data.remote.body.ReceiveOrderBody
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first

class ReceiveOrderRemoteDataSource(
    private val services: ReceiveOrderApi,
    private val externalScope: CoroutineScope,
) {
   suspend fun postActionOrder(
        action: String,
        receiveOrderBody: ReceiveOrderBody,
    ) = services.postActionOrder(action, receiveOrderBody).flowCollector(externalScope)



}