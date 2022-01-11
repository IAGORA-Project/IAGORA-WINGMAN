package com.iagora.wingman.market.domain.repository


import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory

interface IMarketRepository {
    suspend fun getListMarket(): Resource<ListMarket>
    suspend fun getListProduct(idMarket: String): Resource<ListProduct>
    suspend fun getListTypeAndCategory(): Resource<ListTypeAndCategory>
    suspend fun postAddProduct(addProduct: AddProduct): Resource<AddProductFeedBack>
}