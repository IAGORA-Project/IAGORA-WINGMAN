package com.iagora.wingman.receive_order.core

import com.iagora.wingman.receive_order.helper.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.helper.data.remote.response.ResAcceptedOrder
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReceiveOrderApi {
    @POST("{action}")
    suspend fun postActionOrder(
        @Path("action") action: String,
        @Body receiveOrderBody: ReceiveOrderBody,
    ): ResAcceptedOrder
}