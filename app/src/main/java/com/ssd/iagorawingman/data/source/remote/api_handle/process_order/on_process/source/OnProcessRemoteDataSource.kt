package com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.source

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.on_process.OnProcessApi
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.HttpException
import java.io.IOException

class OnProcessRemoteDataSource(private val services: OnProcessApi) {
    fun getAllListWaiting(
        token: String
    ) = flow {
        try {
            val response = services.getAllListWaiting("Bearer $token")
            emit(ApiResponse.Success(response))
        } catch (e: IOException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        } catch (e: HttpException) {
            Log.e("RESPONSE_FAILURE", e.toString())
            emit(ApiResponse.Error(e.toString()))
        }
    }.flowOn(Dispatchers.IO)
}