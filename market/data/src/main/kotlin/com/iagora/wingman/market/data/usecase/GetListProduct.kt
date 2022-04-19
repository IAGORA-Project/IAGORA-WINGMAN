package com.iagora.wingman.market.data.usecase

import com.iagora.wingman.core.data.model.market.data.MarketWithProduct
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.domain.usecase.IGetListProduct
import com.iagora.wingman.market.helper.model.response.ListProduct

class GetListProduct(private val repository: IMarketRepository) : IGetListProduct {
    override suspend fun invoke(idMarket: String): Resource<MarketWithProduct> =
        repository.getListProduct(idMarket)
}