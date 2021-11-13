package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.source

import android.util.Log
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.ProcessOrderApi
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.body.HandlingFeeBody
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import retrofit2.HttpException
import java.io.IOException

class ProcessOrderRemoteDataSource(
    private val services: ProcessOrderApi,
    private val externalScope: CoroutineScope
) {
    fun getAllListWaiting(
        typeWaiting: String
    ) = flow {
        try {
            val response = services.getAllListWaiting(typeWaiting)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun getDetailWaiting(
        idTransaction: String,
        typeWaiting: String
    ) = flow {
        try {
            val response =
                services.getDetailListWaiting(idTransaction, typeWaiting)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun postBargainPrice(
        bargainBody: BargainBody,
    ) = flow {
        try {
            val response = services.postBargainPrice(bargainBody)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun postNewHandlingFee(
        idTransaction: String,
        handlingFeeBody: HandlingFeeBody,
    ) = flow {
        try {
            val response =
                services.postNewHandlingFee(idTransaction, handlingFeeBody)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

    fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ) = flow {
        try {
            val response =
                services.postActionTransaction(idTransaction, typeAction)
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)
}