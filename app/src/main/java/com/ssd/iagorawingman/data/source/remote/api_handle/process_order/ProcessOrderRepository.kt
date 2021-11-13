package com.ssd.iagorawingman.data.source.remote.api_handle.process_order

import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.model.ProcessOrder
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.domain.repository.IProcessOrderRepository
import com.ssd.iagorawingman.data.source.remote.api_handle.process_order.source.ProcessOrderRemoteDataSource
import com.ssd.iagorawingman.data.source.remote.body.BargainBody
import com.ssd.iagorawingman.data.source.remote.body.HandlingFeeBody
import com.ssd.iagorawingman.data.source.remote.network.ApiResponse
import com.ssd.iagorawingman.utils.DataMapper
import com.ssd.iagorawingman.utils.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ProcessOrderRepository(
    private val orderRemoteDataSource: ProcessOrderRemoteDataSource,
) : IProcessOrderRepository {


    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.getAllListWaiting(
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
                orderRemoteDataSource.postBargainPrice(body)
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        DataMapper.mapResponseBargainPriceToDomainBargainPrice(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage, null))
            }
        }


    override fun postNewHandlingFee(
        idTransaction: String,
        handlingFeeBody: HandlingFeeBody,
    ): Flow<Resource<ProcessOrder.Global>> = flow {
        emit(Resource.loading("true", null))
        when (val response =
            orderRemoteDataSource.postNewHandlingFee(
                idTransaction,
                handlingFeeBody)
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
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>> =

        flow {
            emit(Resource.loading("true", null))
            when (val response =
                orderRemoteDataSource.postActionTransaction(

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