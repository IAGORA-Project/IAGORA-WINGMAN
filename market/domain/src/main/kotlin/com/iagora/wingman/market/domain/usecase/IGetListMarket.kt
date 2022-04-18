package com.iagora.wingman.market.domain.usecase


import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.helper.model.response.ListMarket

interface IGetListMarket {
    suspend operator fun invoke(): Resource<ListMarket>
}