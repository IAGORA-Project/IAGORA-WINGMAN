package com.iagora.wingman.market.core.source.remote

import com.iagora.wingman.core.source.remote.network.flowCollector
import com.iagora.wingman.market.core.MarketApi
import com.iagora.wingman.market.helper.data.remote.body.AddProductBody
import kotlinx.coroutines.CoroutineScope

class MarketRemoteDataSource(
    private val services: MarketApi,
    private val externalScope: CoroutineScope,
) {
    suspend fun getListMarket() = services.getListMarket().flowCollector(externalScope)

    suspend fun getListProduct(idMarket: String) =
        services.getListProduct(idMarket).flowCollector(externalScope)

    suspend fun getListTypeAndCategory() =
        services.getListTypeAndCategory().flowCollector(externalScope)

    suspend fun postAddProduct(addProductBody: AddProductBody) =
        services.postAddProduct(addProductBody).flowCollector(externalScope)
}