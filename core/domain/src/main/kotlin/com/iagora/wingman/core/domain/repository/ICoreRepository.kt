package com.iagora.wingman.core.domain.repository

import com.iagora.wingman.core.util.Resource

interface ICoreRepository {
    suspend fun getSESSID(): Resource<String>
}