package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process

import com.ssd.iagorawingman.data.source.remote.response.ResGetListWaitingOnProcessOrder
import retrofit2.http.GET
import retrofit2.http.Header

interface OnProcessApi {
    @GET("transaction/WAITING_CONFIRMATION")
    suspend fun getAllListWaiting(
        @Header("Authorization") authorization: String,
    ):ResGetListWaitingOnProcessOrder
}