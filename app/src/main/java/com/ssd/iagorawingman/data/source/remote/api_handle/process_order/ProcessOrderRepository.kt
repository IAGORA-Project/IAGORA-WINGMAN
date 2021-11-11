package com.ssd.iagorawingman.data.source.remote.api_handle.process_order

import com.ssd.iagorawingman.BuildConfig
import com.ssd.iagorawingman.data.source.local.shared_handle.auth.SharedAuthRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository.IProcessOrderRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.source.ProcessOrderRemoteDataSource
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import com.ssd.iagorawingman.utils.DataMapper
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ProcessOrderRepository(
    private val orderRemoteDataSource: ProcessOrderRemoteDataSource,
    sharedAuth: SharedAuthRepository
) : IProcessOrderRepository {


    private val token = sharedAuth.getAuth(BuildConfig.KEY_SHARED_PREFERENCE_AUTH)

    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.getAllListWaiting(
                    token?.success?.token as String,
                    typeWaiting
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseWaitingListToDomainWaitingList(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }


    override fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.getDetailWaiting(
                    token?.success?.token as String,
                    idTransaction,
                    typeWaiting
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseGetDetailWaitingListOnProcessOrderToDetailWaitingOnProcess(
                            response.data
                        )
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }

    override fun postBargainPrice(body: BargainBody): Flow<Resource<ProcessOrder.Global>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.postBargainPrice(token?.success?.token as String, body)
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseBargainPriceToDomainBargainPrice(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }

    override fun postActionTransaction(
        idTransaction: String,
        typeAction: String
    ): Flow<Resource<ProcessOrder.Global>> =

        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.postActionTransaction(
                    token?.success?.token as String,
                    idTransaction,
                    typeAction
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseBargainPriceToDomainBargainPrice(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }
}