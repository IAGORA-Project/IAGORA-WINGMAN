package com.iagora.wingman.core.data.usecase

import com.iagora.wingman.core.domain.repository.ICoreRepository
import com.iagora.wingman.core.domain.usecase.IGetSESSID
import com.iagora.wingman.core.util.Resource
import com.iagora.wingman.core.util.SimpleResource

class GetSESSID(private val repository: ICoreRepository) : IGetSESSID {
    override suspend fun invoke(): Resource<String> = repository.getSESSID()
}