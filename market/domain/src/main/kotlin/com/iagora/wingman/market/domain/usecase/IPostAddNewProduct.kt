package com.iagora.wingman.market.domain.usecase

import com.iagora.wingman.core.data.model.market.data.Product
import com.iagora.wingman.core.data.model.market.remote.ReqAddNewProduct
import com.iagora.wingman.core.util.Resource
import okhttp3.MultipartBody
import okhttp3.RequestBody

interface IPostAddNewProduct {
    suspend operator fun invoke(map: Map<String, RequestBody>, image: MultipartBody.Part):Resource<Product>
}