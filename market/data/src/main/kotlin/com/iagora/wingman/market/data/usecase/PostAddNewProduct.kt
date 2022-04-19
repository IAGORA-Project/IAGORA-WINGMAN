package com.iagora.wingman.market.data.usecase

import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.data.model.market.data.Product
import com.iagora.wingman.market.data.repository.MarketRepository
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.domain.usecase.IPostAddNewProduct
import okhttp3.MultipartBody
import okhttp3.RequestBody

class PostAddNewProduct(private val repository: IMarketRepository): IPostAddNewProduct {
    override suspend fun invoke(
        map: Map<String, RequestBody>,
        image: MultipartBody.Part
    ): Resource<Product> {
        return repository.postAddNewProduct(image, map)
    }
}