package com.ssd.iagorawingman.data.source.remote.api_handle.process_order


import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.response.ResGetProcessOrder
import retrofit2.http.*

interface ProcessOrderApi {
    @GET("transaction/{type_waiting}")
    suspend fun getAllListWaiting(
        @Header("Authorization") authorization: String,
        @Path("type_waiting") typeWaiting: String
    ): ResGetProcessOrder.ResGetListWaitingOnProcessOrder

    @GET("transaction/{type_waiting}/{id_transaction}")
    suspend fun getDetailListWaiting(
        @Header("Authorization") authorization: String,
        @Path("id_transaction") idTransaction: String,
        @Path("type_waiting") typeWaiting: String
    ): ResGetProcessOrder.ResGetDetailWaitingListOnProcessOrder

    @POST("transaction/tawar")
    suspend fun postBargainPrice(
        @Header("Authorization") authorization: String,
        @Body bargainBody: BargainBody
    ): ResGetProcessOrder.ResGlobal

    @POST("transaction/{id_transaction}/{type_action}")
    suspend fun postActionTransaction(
        @Header("Authorization") authorization: String,
        @Path("id_transaction") idTransaction: String,
        @Path("type_action") typeAction: String
    ): ResGetProcessOrder.ResGlobal
}