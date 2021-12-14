package com.iagora.wingman.market.core

import com.iagora.wingman.core.source.remote.network.ApiResponse
import com.iagora.wingman.helper.Resource
import com.iagora.wingman.market.core.domain.repository.IMarketRepository
import com.iagora.wingman.market.core.source.remote.MarketRemoteDataSource
import com.iagora.wingman.market.helper.mapper.MapperAddProduct.toBody
import com.iagora.wingman.market.helper.mapper.MapperAddProductFeedback.toModel
import com.iagora.wingman.market.helper.mapper.MapperListMarket.toModel
import com.iagora.wingman.market.helper.mapper.MapperListProduct.toModel
import com.iagora.wingman.market.helper.mapper.MapperListTypeAndCategory.toModel
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

class MarketRepository(
    private val marketRemoteDataSource: MarketRemoteDataSource,
) : IMarketRepository {
    override fun getListMarket(): Flow<Resource<ListMarket>> = flow {
        emit(Resource.loading())
        when (val response = marketRemoteDataSource.getListMarket().first()) {
            is ApiResponse.Success -> emit(Resource.success(response.data.toModel()))
            is ApiResponse.Error -> emit(Resource.error(response.errorMessage))
        }
    }

    override fun getListProduct(idMarket: String): Flow<Resource<ListProduct>> = flow {
        emit(Resource.loading())
        when (val response = marketRemoteDataSource.getListProduct(idMarket).first()) {
            is ApiResponse.Success -> emit(Resource.success(response.data.toModel()))
            is ApiResponse.Error -> emit(Resource.error(response.errorMessage))
        }
    }

    override fun getListTypeAndCategory(): Flow<Resource<ListTypeAndCategory>> = flow {
        emit(Resource.loading())
        when (val response = marketRemoteDataSource.getListTypeAndCategory().first()) {
            is ApiResponse.Success -> emit(Resource.success(response.data.toModel()))
            is ApiResponse.Error -> emit(Resource.error(response.errorMessage))
        }
    }

    override fun postAddProduct(addProduct: AddProduct): Flow<Resource<AddProductFeedBack>> =
        flow {
            emit(Resource.loading())
            when (val response =
                marketRemoteDataSource.postAddProduct(addProduct.toBody()).first()) {
                is ApiResponse.Success -> emit(Resource.success(response.data.toModel()))
                is ApiResponse.Error -> emit(Resource.error(response.errorMessage))
            }
        }

}