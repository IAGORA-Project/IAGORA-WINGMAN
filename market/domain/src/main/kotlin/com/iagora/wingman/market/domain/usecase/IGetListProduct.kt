package com.iagora.wingman.market.domain.usecase


import com.iagora.wingman.core.data.model.market.data.MarketWithProduct
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.helper.model.response.ListProduct

interface IGetListProduct {
    suspend operator fun invoke(idMarket:String): Resource<MarketWithProduct>
}