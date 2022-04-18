package com.iagora.wingman.market.domain.repository


import com.iagora.wingman.core.data.model.market.data.MarketWithProduct
import com.iagora.wingman.core.data.model.market.data.Product
import com.iagora.wingman.core.data.model.market.data.ProductCategory
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.helper.model.body.AddProduct
import com.iagora.wingman.market.helper.model.response.AddProductFeedBack
import com.iagora.wingman.market.helper.model.response.ListMarket
import com.iagora.wingman.market.helper.model.response.ListProduct
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IMarketRepository {
    suspend fun getListMarket(): Resource<ListMarket>
    suspend fun getListProduct(idMarket: String): Resource<MarketWithProduct>
    suspend fun getListTypeAndCategory(): Resource<ArrayList<ProductCategory>>
    suspend fun postAddNewProduct(
        image: MultipartBody.Part,
        map: Map<String, RequestBody>
    ): Resource<Product>
}