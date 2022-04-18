package com.iagora.wingman.process_order.data.remote


import com.iagora.wingman.process_order.core.data.remote.body.BargainBody
import com.iagora.wingman.process_order.core.data.remote.body.HandlingFeeBody
import com.iagora.wingman.process_order.data.remote.response.ResProcessOrder
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface ProcessOrderApi {
    @GET("transaction/{type_waiting}")
    suspend fun getAllListWaiting(
        @Path("type_waiting") typeWaiting: String,
    ): ResProcessOrder.ResListOrder

    @GET("transaction/{type_waiting}/{id_transaction}")
    suspend fun getDetailListWaiting(
        @Path("id_transaction") idTransaction: String,
        @Path("type_waiting") typeWaiting: String,
    ): ResProcessOrder.ResDetailOrder

    @POST("transaction/tawar")
    suspend fun postBargainPrice(
        @Body bargainBody: BargainBody,
    ): ResProcessOrder.ResGlobal

    @POST("transaction/{id_transaction}/{type_action}")
    suspend fun postActionTransaction(
        @Path("id_transaction") idTransaction: String,
        @Path("type_action") typeAction: String,
    ): ResProcessOrder.ResGlobal

    @POST("transaction/{id_transaction}/change_handling_fee")
    suspend fun postNewHandlingFee(
        @Path("id_transaction") idTransaction: String,
        @Body handlingFeeBody: HandlingFeeBody,
    ): ResProcessOrder.ResGlobal
}