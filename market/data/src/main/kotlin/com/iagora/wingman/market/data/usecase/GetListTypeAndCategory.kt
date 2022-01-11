package com.iagora.wingman.market.data.usecase

import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.domain.repository.IMarketRepository
import com.iagora.wingman.market.domain.usecase.IGetListTypeAndCategory
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory

class GetListTypeAndCategory(private val repository: IMarketRepository) : IGetListTypeAndCategory {
    override suspend fun invoke(): Resource<ListTypeAndCategory> =
        repository.getListTypeAndCategory()
}