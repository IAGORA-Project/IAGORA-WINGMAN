package com.iagora.wingman.market.data.usecase


import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.domain.usecase.IGetListMarket
import com.iagora.wingman.market.helper.model.response.ListMarket

class GetListMarket(private val repository: IMarketRepository) : IGetListMarket {
    override suspend fun invoke(): Resource<ListMarket> = repository.getListMarket()
}