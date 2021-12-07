package com.iagora.wingman.process_order.core

import com.iagora.wingman.core.source.remote.network.ApiResponse
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.process_order.core.data.remote.ProcessOrderRemoteDataSource
import com.iagora.wingman.process_order.core.domain.repository.IProcessOrderRepository
import com.iagora.wingman.process_order.core.helper.DataMapperProcessOrder.mapBargainToBargainBody
import com.iagora.wingman.process_order.core.helper.DataMapperProcessOrder.mapHandlingFeeToHandlingFeeBody
import com.iagora.wingman.process_order.core.helper.DataMapperProcessOrder.mapResponseBargainPriceToModelBargainPrice
import com.iagora.wingman.process_order.core.helper.DataMapperProcessOrder.mapResponseGetDetailWaitingListOnProcessOrderToModelDetailWaitingOnProcess
import com.iagora.wingman.process_order.core.helper.DataMapperProcessOrder.mapResponseWaitingListToModelWaitingList
import com.iagora.wingman.process_order.helper.model.body.Bargain
import com.iagora.wingman.process_order.helper.model.body.HandlingFee
import com.iagora.wingman.process_order.helper.model.response.ProcessOrder
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class ProcessOrderRepository(
    private val processOrderRemoteDataSource: ProcessOrderRemoteDataSource,
) : IProcessOrderRepository {


    override fun getAllListWaiting(typeWaiting: String): Flow<Resource<ProcessOrder.ListWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                processOrderRemoteDataSource.getAllListWaiting(
                    typeWaiting
                ).first()
                    ) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        mapResponseWaitingListToModelWaitingList(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }


    override fun getDetailListWaiting(
        idTransaction: String,
        typeWaiting: String,
    ): Flow<Resource<ProcessOrder.DetailWaitingOnProcess>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                processOrderRemoteDataSource.getDetailWaiting(
                    idTransaction,
                    typeWaiting
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        mapResponseGetDetailWaitingListOnProcessOrderToModelDetailWaitingOnProcess(
                            response.data
                        )
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }

    override fun postBargainPrice(body: Bargain): Flow<Resource<ProcessOrder.Global>> =
        flow {
            emit(Resource.loading("true", null))
            when (val response =
                processOrderRemoteDataSource.postBargainPrice(mapBargainToBargainBody(body))
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        mapResponseBargainPriceToModelBargainPrice(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }


    override fun postNewHandlingFee(
        idTransaction: String,
        handlingFee: HandlingFee,
    ): Flow<Resource<ProcessOrder.Global>> = flow {
        emit(Resource.loading("true", null))
        when (val response =
            processOrderRemoteDataSource.postNewHandlingFee(
                idTransaction,
                mapHandlingFeeToHandlingFeeBody(handlingFee))
                .first()) {
            is ApiResponse.Success -> emit(
                Resource.success(
                    mapResponseBargainPriceToModelBargainPrice(response.data)
                )
            )
            is ApiResponse.Error -> emit(Resource.error(
                response.errorMessage,
                null))
        }
    }

    override fun postActionTransaction(
        idTransaction: String,
        typeAction: String,
    ): Flow<Resource<ProcessOrder.Global>> =

        flow {
            emit(Resource.loading("true", null))
            when (val response =
                processOrderRemoteDataSource.postActionTransaction(

                    idTransaction,
                    typeAction
                )
                    .first()) {
                is ApiResponse.Success -> emit(
                    Resource.success(
                        mapResponseBargainPriceToModelBargainPrice(response.data)
                    )
                )
                is ApiResponse.Error -> emit(Resource.error(
                    response.errorMessage,
                    null))
            }
        }
}