package com.iagora.wingman.core.source.remote.network

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.shareIn
import retrofit2.HttpException
import java.io.IOException

sealed class ApiResponse<out R> {
    data class Success<out T>(val data: T) : ApiResponse<T>()
    data class Error(val errorMessage: String) : ApiResponse<Nothing>()


}

fun <T>  T.flowCollector(externalScope: CoroutineScope) = flow {
    try {
        emit(ApiResponse.Success(this@flowCollector))
    } catch (e: IOException) {
        Log.e("RESPONSE_FAILURE", e.toString())
        emit(ApiResponse.Error(e.toString()))
    } catch (e: HttpException) {
        Log.e("RESPONSE_FAILURE", e.toString())
        emit(ApiResponse.Error(e.toString()))
    }
}.shareIn(externalScope, SharingStarted.WhileSubscribed(), 0)

