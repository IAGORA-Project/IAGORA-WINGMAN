package com.iagora.wingman.market.domain.usecase

import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.market.helper.model.response.ListTypeAndCategory

interface IGetListTypeAndCategory {
    suspend operator fun invoke():Resource<ListTypeAndCategory>
}