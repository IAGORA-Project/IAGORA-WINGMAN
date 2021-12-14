package com.iagora.wingman.market.core.domain.repository

import com.iagora.wingman.helper.Resource
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import kotlinx.coroutines.flow.Flow

interface IMarketRepository {
    fun getListMarket(): Flow<Resource<ListMarket>>
    fun getListProduct(idMarket: String): Flow<Resource<ListProduct>>
    fun getListTypeAndCategory(): Flow<Resource<ListTypeAndCategory>>
    fun postAddProduct(addProduct: AddProduct): Flow<Resource<AddProductFeedBack>>
}