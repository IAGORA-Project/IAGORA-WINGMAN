package com.iagora.wingman.process_order.core.source.remote

import com.iagora.wingman.core.source.remote.network.flowCollector
import com.iagora.wingman.process_order.core.ProcessOrderApi
import com.iagora.wingman.process_order.helper.data.remote.body.BargainBody
import com.iagora.wingman.process_order.helper.data.remote.body.HandlingFeeBody
import kotlinx.coroutines.CoroutineScope

class ProcessOrderRemoteDataSource(
    private val services: ProcessOrderApi,
    private val externalScope: CoroutineScope,
) {
    suspend fun getAllListWaiting(
        typeWaiting: String,
    ) = services.getAllListWaiting(typeWaiting).flowCollector(externalScope)

    suspend fun getDetailWaiting(
        idTransaction: String,
        typeWaiting: String,
    ) = services.getDetailListWaiting(idTransaction, typeWaiting).flowCollector(externalScope)

    suspend fun postBargainPrice(
        bargainBody: BargainBody,
    ) = services.postBargainPrice(bargainBody).flowCollector(externalScope)

    suspend fun postNewHandlingFee(
        idTransaction: String,
        handlingFeeBody: HandlingFeeBody,
    ) = services.postNewHandlingFee(idTransaction, handlingFeeBody).flowCollector(externalScope)

    suspend fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ) = services.postActionTransaction(idTransaction, typeAction).flowCollector(externalScope)
}