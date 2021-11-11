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
<<<<<<< HEAD
                orderRemoteDataSource.getAllListWaiting(
                    token?.success?.token as String,
                    typeWaiting
                )
=======
                orderRemoteDataSource.getAllListWaiting(token?.success?.token!!, typeWaiting)
>>>>>>> 115d2f0db44d1a74226682896bcbdd9845ef69a6
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
<<<<<<< HEAD
                    token?.success?.token as String,
=======
                    token?.success?.token!!,
>>>>>>> 115d2f0db44d1a74226682896bcbdd9845ef69a6
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
<<<<<<< HEAD
                orderRemoteDataSource.postBargainPrice(token?.success?.token as String, body)
                    .first()) {
=======
                orderRemoteDataSource.postBargainPrice(token?.success?.token!!, body).first()) {
>>>>>>> 115d2f0db44d1a74226682896bcbdd9845ef69a6
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
<<<<<<< HEAD
                    token?.success?.token as String,
=======
                    token?.success?.token!!,
>>>>>>> 115d2f0db44d1a74226682896bcbdd9845ef69a6
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