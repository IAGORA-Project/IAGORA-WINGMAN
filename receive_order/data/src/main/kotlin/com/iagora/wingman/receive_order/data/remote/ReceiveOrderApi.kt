package com.iagora.wingman.receive_order.data.remote

import com.iagora.wingman.receive_order.data.remote.body.ReceiveOrderBody
import com.iagora.wingman.receive_order.data.remote.response.ResAcceptedOrder
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface ReceiveOrderApi {

    @POST("{action}")
    suspend fun postOrderAction(
        @Path("action") action: String,
        @Body request: ReceiveOrderBody,
    ): ResAcceptedOrder
}