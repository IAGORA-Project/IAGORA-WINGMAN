//package com.iagora.wingman.app.utils
//
//import com.iagora.wingman.core.util.Resource
//import com.iagora.wingman.helper.Resource
//import kotlinx.coroutines.flow.Flow
//import kotlinx.coroutines.flow.channelFlow
//import kotlinx.coroutines.flow.collect
//import kotlinx.coroutines.flow.first
//import kotlinx.coroutines.launch
//
//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> Flow<ResultType>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType) -> Boolean = { true },
//    crossinline onFetchSuccess: () -> Unit = { },
//    crossinline onFetchFailed: (Throwable) -> Unit = { },
//) = channelFlow {
//    val data = query().first()
//
//    if (shouldFetch(data)) {
//        val loading = launch {
//            query().collect { send(Resource.loading("", null)) }
//        }
//
//        try {
//            saveFetchResult(fetch())
//            onFetchSuccess()
//            loading.cancel()
//            query().collect { send(Resource.success(it)) }
//        } catch (t: Throwable) {
//            onFetchFailed(t)
//            loading.cancel()
//            query().collect { send(Resource.error(t.toString(), it)) }
//        }
//    } else {
//        query().collect { send(Resource.success(it)) }
//    }
//}